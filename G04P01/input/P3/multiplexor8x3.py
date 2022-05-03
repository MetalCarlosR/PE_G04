

import os
from unittest import result


def saveString(bin):
    filename = input("Nombre del archivo: ")

    file = open(os.path.dirname(os.path.realpath(__file__)) + "/" + filename + ".txt", "w")

    file.write(text)


def addToString(bin):
    global text

    for i in bin:
        text += str(i * 1) + " "

    A = bin[0:3]
    D = bin[3:]

    ands = [None] * 8

    ands[0] = ((not A[0]) * (not A[1]) * (not A[2]) * D[1] )
    ands[1] = ((A[0]) * (not A[1]) * (not A[2]) * D[0] )
    ands[2] = ((not A[0]) * (A[1]) * (not A[2]) * D[2] )
    ands[3] = ((A[0]) * (A[1]) * (not A[2]) * D[3] )
    ands[4] = ((not A[0]) * (not A[1]) * (A[2]) * D[4] )
    ands[5] = ((A[0]) * (not A[1]) * (A[2]) * D[5] )
    ands[6] = ((not A[0]) * (A[1]) * (A[2]) * D[6] )
    ands[7] = ((A[0]) * (A[1]) * (A[2]) * D[7] )

    result = False
    for r in ands:
        result += r

    text += str(result * 1) + "\n"

def generateBinaryString(n, bin, i):

    if i == n:
        addToString(bin)
        return

    bin[i] = False
    generateBinaryString(n,bin,i +1)
    bin[i] = True
    generateBinaryString(n,bin,i +1)




bin = [None] * 11

text = ""

generateBinaryString(11,bin,0)

saveString(text)
