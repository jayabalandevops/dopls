import groovy.json.JsonSlurperClassic

cDelivery = readFile "$wrks/config/cpl.json"
def jString = new JsonSlurperClassic().parseText(cDelivery)
def bStr = jString.btool
bStr = "$bStr"+" -f "+'"appl/pom.xml"'+" "+jString.target
sh "$bStr" 