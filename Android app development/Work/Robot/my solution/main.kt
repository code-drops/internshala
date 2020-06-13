fun main(){
    var atom = Robot("atom")

    // 1. Ring the Alarm
    // Setting the alarm with time,a specific message and set fields false for the day on which alarm should not ring
    atom.ringAlarm("6:00 AM","wake up")
    println()

    // 2. Make coffee - m2t6v5
    atom.makeCoffee()
    println()

    // 3. Heat the water with temperature and two days on which bathing is not done
    atom.heatWater("30","saturday","sunday")
    println()

    // 4. keep the books according to pre-defined schedule
    atom.packBag()
    println()

    // 5. make breakfast or lunch, if both the fields are null then it will choose random item from the list
    atom.makeFood("Bread pakora",null)
    println()

    // 6. Provide two clothes to iron them
    atom.ironCloth("Black shirt","Blue jeans")
}