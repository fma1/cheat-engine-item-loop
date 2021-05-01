package com.github.fma

import scala.annotation.tailrec

object CheatEngineItemLoop extends App {
  // Needed for implicit conversion of INITIAL_ADDRESS
  implicit def hex2long(hex: String): Long = java.lang.Long.parseLong(hex, 16)

  val NUM_ITERATIONS = 55
  val INITIAL_ADDRESS = "4010DEC72"
  val ADDRESS_SIZE = 4

  def printXML(): Unit = {
    @tailrec
    def printXMLHelper(currAddr: Long, currIteration: Int): Unit = {
      if (currIteration > NUM_ITERATIONS) {
        ()
      } else {
        val currAddrStr = currAddr.toHexString
        val str = s"""|    <CheatEntry>
                      |      <ID>4</ID>
                      |      <Description>Item #$currIteration</Description>
                      |      <LastState Value="99" RealAddress="${currAddrStr}"/>
                      |      <ShowAsSigned>0</ShowAsSigned>
                      |      <VariableType>Custom</VariableType>
                      |      <CustomType>2 Byte Big Endian</CustomType>
                      |      <Address>${currAddrStr}</Address>
                      |    </CheatEntry>""".stripMargin
        println(str)
        printXMLHelper(currAddr + 4, currIteration + 1)
      }
    }
    printXMLHelper(INITIAL_ADDRESS, 1)
  }

  printXML()
}
