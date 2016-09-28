""" HANGMAN!!
Name: Megan Landau

Purpose: This program reads in a .txt file and enables the user to play Hangman using 
a simple GUI. 

Authenticity: I did work through some issues with the help of other students, but I
certify that this program is all my own work except for Classes Noose and Scaffold - those 
are Professor Randall Alexander’s.

wordlist.txt needs to be included to run this! It is just a list of words to play
the game with!

"""
from graphics import *
from random import *
from time import sleep

class Words:
    '''reads a file of words and creates a list of words'''
    def __init__(self,filename):
        infile = open(filename, 'r')
        file_string = infile.read()
        infile.close()
        self.word_list = file_string.split('\n')
        del self.word_list[-1]
        
    def get_word(self):
        '''returns a random word from the list'''
        word = self.word_list.pop(randrange(len(self.word_list)))
        return word
    
    def __list__(self):
        return list(self.word_list)

class Guess:
    '''creates an object with two lists. One contains the letters of the
    word and the other is the same length with blanks. When a letter is
    guessed, the letter in the word is swapped with the blank in that
    position, i.e. ['c','a','t'] and ['_','_','_'] that are created
    would change to ['c','_','t'] and ['_','a','_'] when the letter a
    is guessed'''
    def __init__ (self,word):
        self.word = word
        self.word_lst = []
        self.blank_lst = []

        for letter in self.word:
            self.blank_lst.append('_')
            self.word_lst.append(letter)

        self.guessed_ltrs = []
        self.total_guesses = 0

        
    def missed(self):
        '''returns the word string that could not be guessed'''
        return str(self.word)
        
    def guess_letter(self,letter):
        '''uncovers letters that match the guess, counts the bad guesses
        and keeps track of the letters guessed. It returns a number, 0,
        if the letter has already been guessed, 1 if the letter is NOT
        in the word and 2 if the letter IS in the word'''

        if letter in self.guessed_ltrs:
            print("You already guessed that, dummy")
            return 0
        elif letter not in self.word_lst:
            self.guessed_ltrs.append(letter)
            self.guessed_ltrs.sort()
            self.total_guesses += 1
            return 1
        else:
            for i in range(len(self.word_lst)):
                if self.word_lst[i] == letter:
                    self.blank_lst[i] = self.word_lst[i]
            return 2

        
    def gameover(self):
        '''returns Boolean, T if word is guessed or the number of guesses
        has exceeded the limit and F otherwise'''
        return self.total_guesses == 7 or self.blank_lst == self.word_lst
   
    def num_of_guesses(self):
        '''returns a STRING with the number of remaining guesses'''
        return str(7 - self.total_guesses)
        
    def letters_guessed(self):
        '''returns a string, in alphabetical order, of all the letters
        that have been guessed'''
        self.guessed_ltrs.sort()
        return str(" ".join(self.guessed_ltrs))
        
    def get_guess(self):
        '''returns a string with the letters in the word and _ for each
        unguessed letter separated by spaces exactly like the __str__'''
        return str(" ".join(self.blank_lst))
        
    def   __str__ (self):
        '''returns a string with the letters in the word and _ for each'''
        return str(" ".join(self.blank_lst))

class Scaffold:
    def __init__(self, win):
        self.win = win
        self.scaffold = [Polygon(Point(350, 25), Point(375, 25), Point(450, 100), Point(450, 125)),
                 Rectangle(Point(450, 25), Point(475, 475)),
                 Rectangle(Point(325, 25), Point(475, 50)),
                 Rectangle(Point(325, 450), Point(475, 475))]
        for piece in self.scaffold:
            piece.setFill("Tan")
            piece.draw(self.win)

class Noose:
    def __init__(self,win):
        '''creates a Noose object with 7 sections that can be drawn one at a
        time in the win canvas'''
        self.win = win

        self.head = Oval(Point(325, 400), Point(365,450))
        self.head.setFill("BlanchedAlmond")
        self.body = Polygon(Point(345, 400), Point(385, 380), Point(385,240),
                            Point(305,240), Point(305,380))
        self.body.setFill("LightSteelBlue")
        self.armL = Polygon(Point(305, 380), Point(315, 365),
                            Point(315,255), Point(290,255), Point(290,365))
        self.armL.setFill("LightSteelBlue")
        self.armR = Polygon(Point(385, 380), Point(395, 365),
                            Point(395,255), Point(370,255), Point(370,365))
        self.armR.setFill("LightSteelBlue")
        self.legL = Rectangle(Point(340, 125), Point(305,240))
        self.legL.setFill("DarkKhaki")
        self.legR = Rectangle(Point(350, 125), Point(385,240))
        self.legR.setFill("DarkKhaki")
        self.footL = Polygon(Point(322, 130), Point(308, 120),
                             Point(304, 110), Point(338, 110), Point(336, 120))
        self.footL.setFill("RosyBrown")
        self.footR = Polygon(Point(366, 130), Point(352, 120),
                             Point(348, 110), Point(382, 110), Point(380, 120))
        self.footR.setFill("RosyBrown")
        
        self.parts = [self.head, self.body, self.armL, self.armR, self.legL,
                      self.legR, self.footL, self.footR]

        self.total_guesses = 0
        self.partsShowing = []

    def wrong(self, body_part):
        '''draws another of the 7 sections to the noose platform and/or figure'''

        if body_part == 6:
            self.parts[7].draw(self.win)
            self.parts[6].draw(self.win)
        elif body_part not in self.partsShowing:
            self.partsShowing.append(body_part)
            self.total_guesses += 1
            self.parts[body_part].draw(self.win)

    def clearNoose(self):
        for piece in self.parts:
            piece.undraw()

    
def main():
    fn = 'wordlist.txt'
    word_bank = Words(fn)
    win = GraphWin("Hangman!", 500, 500)
    win.setCoords(0,0,500,500)
    playGameButton = Button(win, Point(175,350), 200, 100, "PLAY HANGMAN!", "PaleTurquoise")
    closeWindowButton = Button(win, Point(175,150), 200,100, "CLOSE WINDOW", "Tomato")
    playGameButton.activate()
    closeWindowButton.activate()

    scaff = Scaffold(win)

    while True:
        p = win.getMouse()
        if playGameButton.clicked(p):
            playGameButton.deactivate()
            closeWindowButton.deactivate()
            d_win, ns = play_one_game(win, word_bank)
            while True:
                if playAgain(win, word_bank, d_win, ns):
                    play_one_game(win, word_bank)
                else:
                    break
            break
        else:
            if closeWindowButton.clicked(p):
                win.close()
                break           


def settingTheGame(win):
    mysteryText = Text(Point(150,400), "Mystery Word:\n{}".format(''))
    mysteryText.setSize(22)
    enterText = Text(Point(150,325), "Enter a letter and click ENTER")
    enterText.setSize(18)
    entryBox = Entry(Point(105, 275), 2)
    entryBox.setFill("lightgray")
    enterButton = Button(win, Point(190,275), 50,50,"ENTER", "PaleTurquoise")
    guessesText = Text(Point(150,215), "{} guesses left".format(""))
    guessesText.setSize(22)
    ltrsGuessedText = Text(Point(150,155), "Letters guessed:\n{}".format(''))
    ltrsGuessedText.setSize(18)
    did_you_win = Text(Point(150, 130), "")
    did_you_win.setSize(22)
    return ltrsGuessedText, guessesText, mysteryText, entryBox, enterButton, enterText, did_you_win


def play_one_game(w, wb):
    settingTheGame(w)
    answer = wb.get_word()
    print("Answer is: ", answer)
    guess = Guess(answer)
    ltrsGuessedText, guessesText, mysteryText, entryBox, enterButton, enterText, did_you_win = settingTheGame(w)

    ltrsGuessedText.setText("Letters guessed:\n{}".format(guess.letters_guessed()))
    ltrsGuessedText.draw(w)
    guessesText.setText("{} guesses left".format(guess.num_of_guesses()))
    guessesText.draw(w)
    mysteryText.setText("Mystery Word:\n{}".format(guess.get_guess()))
    mysteryText.draw(w)
    enterButton.activate()
    entryBox.draw(w)
    enterText.draw(w)
    entryBox.setText('')
    did_you_win.setText('')
    did_you_win.draw(w)

    noose = Noose(w)
    num = 1

    while True:
        while not guess.gameover():
            p = w.getMouse()
            if enterButton.clicked(p):
                isValid = False
                while not isValid:
                    guess_letter = entryBox.getText()[0]
                    if ord(guess_letter) >= ord('a') and ord(guess_letter) <= ord('z'):
                        guess_value = guess.guess_letter(guess_letter)
                        isValid = True
                        if guess_value == 1:
                            noose.wrong(num-1)
                            num += 1
                    else:
                        entryBox.setText('')
                        break
                    ltrsGuessedText.setText("Letters guessed:\n{}".format(guess.letters_guessed()))
                    guessesText.setText("{} guesses left".format(guess.num_of_guesses()))
                    mysteryText.setText("Mystery Word:\n{}".format(guess.get_guess()))
                    entryBox.setText('')
        else:
            break

    if guess.gameover() and int(guess.num_of_guesses()) > 0:
        did_you_win.setText("You won!")
    else:
        did_you_win.setText("Oh no! You lost!")
    ltrsGuessedText.undraw()
    guessesText.undraw()
    mysteryText.undraw()
    enterText.undraw()
    entryBox.undraw()
    enterButton.deactivate()
 
    return did_you_win, noose


def playAgain(window, word_bank, did_you_win, nos):
    
    playAgainText = Text(Point(170,95), "Click YES to play again or NO to quit")
    playAgainText.setSize(20)
    playAgainText.draw(window)

    yesButton = Button(window, Point(115,50), 50,50,"YES", "PaleTurquoise")
    yesButton.activate()

    noButton = Button(window, Point(215,50), 50,50,"NO", "Tomato")
    noButton.activate()

    pt = window.getMouse()

    if yesButton.clicked(pt):
        playAgainText.undraw()
        yesButton.deactivate()
        noButton.deactivate()
        did_you_win.undraw()
        nos.clearNoose()
        return True
    elif noButton.clicked(pt):
        window.close()
        return False
      

class Button:
    """A button is a labeled rectangle in a window.
    It is activated or deactivated with the activate()
    and deactivate() methods. The clicked(p) method
    returns true if the button is active and p is inside it."""

    def __init__(self, win, center, width, height, label, color):
        """ Creates a rectangular button, eg:
        qb = Button(myWin, centerPoint, width, height, 'Quit','yellow') """ 
        self.xmax, self.xmin = center.getX()+width/2.0, center.getX()-width/2.0
        self.ymax, self.ymin = center.getY()+height/2.0, center.getY()-height/2.0
        p1,p2 = Point(self.xmin, self.ymin), Point(self.xmax, self.ymax)
        self.rect = Rectangle(p1,p2)
        self.rect.setFill(color)
        self.label = Text(center, label)
        self.win = win
        self.active = False

    def clicked(self, p):
        "Returns true if button active and p is inside"
        return (self.active and
                self.xmin <= p.getX() <= self.xmax and
                self.ymin <= p.getY() <= self.ymax)

    def activate(self):
        "Sets this button to 'active'and draws the button."
        if self.active == False:
            self.active = True
            self.rect.draw(self.win)
            self.label.draw(self.win)

    def deactivate(self):
        "Sets this button to 'inactive' and undraws the button."
        if self.active == True:
            self.active = False
            self.rect.undraw()
            self.label.undraw()

main()

