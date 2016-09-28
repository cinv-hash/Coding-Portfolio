//
//  GameScene.swift
//  droneflight
//
//  Created by Megan Landau on 9/22/16.
//  Copyright (c) 2016 Megan Landau. All rights reserved.
//

import SpriteKit
import SpeechKit

var backgroundColorCustom = UIColor.whiteColor()

class GameScene: SKScene, SKTransactionDelegate {
    
    var skSession: SKSession?
    var skTransaction: SKTransaction?

    var Drone = SKSpriteNode()
    var myLabel: SKLabelNode!
    var TextureAtlas = SKTextureAtlas()
    var TextureArray = [SKTexture]()
    var flightWobble = [SKAction]()
        
    override func didMoveToView(view: SKView) {
        self.backgroundColor = backgroundColorCustom
        
        myLabel = SKLabelNode(fontNamed: "Arial")
        myLabel.text = "tap and command"
        myLabel.fontSize = 20
        myLabel.position = CGPoint(x: self.size.width / 2, y: 50)
        myLabel.fontColor = UIColor.blackColor()
        
        self.addChild(myLabel)
        
        TextureAtlas = SKTextureAtlas(named: "droneflight")
        
        NSLog("\(TextureAtlas.textureNames)")
        
        //adding animation images to texture array
        TextureArray.append(SKTexture(imageNamed: "2drone.png"))
        TextureArray.append(SKTexture(imageNamed: "3drone.png"))
        TextureArray.append(SKTexture(imageNamed: "4drone.png"))
        TextureArray.append(SKTexture(imageNamed: "1drone.png"))
        
        flightWobble.append(SKAction.moveBy(CGVector(dx: 15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: -15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: -15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: 15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: 15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: -15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: -15, dy: 0), duration: 3))
        flightWobble.append(SKAction.moveBy(CGVector(dx: 15, dy: 0), duration: 3))
        
        //positioning drone animation and adding child node to the view
        Drone = SKSpriteNode(imageNamed: TextureAtlas.textureNames[1])
        Drone.size = CGSize(width: 330, height: 355)
        Drone.position = CGPoint(x: self.size.width / 2, y: 150 )
        self.addChild(Drone)
    
    }

    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        let skSession = SKSession(URL: NSURL( string: "nmsps://NMDPPRODUCTION_Megan_Landau_Drone_Simulator_20160925011829@jzz.nmdp.nuancemobility.net:443"),
                                  appToken: "ba94c616171ffd477c319dfc7c8be7a0d0773564d07df86d4d5fd8b899c24353560a6a6e2967b5a59952a215dd89b6ad00eb11d0680a3db669166e02d8b1562e")

        skTransaction = skSession.recognizeWithType(SKTransactionSpeechTypeDictation, detection: .Short, language: "eng-usa", delegate: self)
    }
    
    
    func transaction(transaction: SKTransaction!, didReceiveRecognition recognition: SKRecognition!) {
        
        myLabel.text = recognition.text.lowercaseString
        
        let words = recognition.text.lowercaseString
        print(recognition.text.lowercaseString)
        
        var strArr = words.characters.split{$0 == " "}.map(String.init)
        
        var distance: CGFloat = 0
        var direction: String = " "
        var goCalled: BooleanType = false
        var inFlight: BooleanType = false
        
        //set up animation actions that can be run using a key to activate/deactivate them
        let land: SKAction = SKAction.moveTo(CGPoint(x: self.size.width / 2, y: 150 ), duration: 2.0 )
        let propellers: SKAction = SKAction.repeatActionForever(SKAction.animateWithTextures(TextureArray, timePerFrame: 0.05))
        let wobble: SKAction = SKAction.sequence(flightWobble)

        //start the propellers
        if strArr[0] == "power"{
            if strArr[1] == "on" {
//                Drone.paused = false
                Drone.runAction(propellers, withKey: "action1")
            }
            if strArr[1] == "off" {
//                Drone.paused = true
                Drone.removeActionForKey("action1")
                Drone.texture = SKTexture(imageNamed: "2drone.png")
            }
        }
        
        // for simple flight commands
        for str in strArr{
            if str == "land" {
                inFlight = false
                Drone.runAction(land)
                Drone.removeActionForKey("wobbleAction")
            }
            // go command
            if str == "go" {
                goCalled = true
                inFlight = true
            }
            //get distance from array
            if let number = Int(str){
                distance = CGFloat(number)
                print("distance = " + String(distance))
            }
            //get direction from array
            if str == "up" {
                direction = str
                print("direction = " + direction)
            }
            if str == "down" {
                direction = str
                print("direction = " + direction)
            }
            //just for fun!
            if str == "charleston" {
                Drone.size.width += 100
                Drone.size.height += 100
                backgroundColor = UIColor(red: 0.6863, green: 0, blue: 0.0431, alpha: 1.0) /* #af000b */
                myLabel.fontColor = UIColor.whiteColor()
            }
            //turn it back to normal
            if str == "normal" {
                Drone.size.width -= 100
                Drone.size.height -= 100
                backgroundColor = UIColor.whiteColor()
                myLabel.fontColor = UIColor.blackColor()
            }
        }
        
        if goCalled {
            let upSequence = SKAction.moveByX(0, y: distance * 10, duration: 1.2 )
            let downSequence = SKAction.moveByX(0, y: -(distance * 10), duration: 1.2)
            
            if direction == "up" {
                Drone.runAction(upSequence)
            }
            if direction == "down" {
                Drone.runAction(downSequence)
            }
        }
        //if drone is in flight, run the wobble animation sequence
        if inFlight{
            Drone.runAction(wobble, withKey: "wobbleAction")
        }
    }
    
    override func update(currentTime: CFTimeInterval) {
        /* Called before each frame is rendered */
    }
}

