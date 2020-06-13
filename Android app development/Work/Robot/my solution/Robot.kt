data class Coffee(var day:String,var typeOfCoffee:String,var sugar_teaspoon:Int,var time:String)
data class Books(var day:String,var book1:String,var book2:String,var book3:String,var book4:String,var book5:String)

class Robot(var name: String){
    var today = "monday"
    // used default arguments here. It will
    fun ringAlarm(time:String,message: String?,sunday: Boolean=true,monday:Boolean=true,tuesday:Boolean=true,wednesday:Boolean=true,thursday:Boolean=true,friday:Boolean=true,saturday:Boolean=true){

        // the alarm will only ring if the value is true for the that day and it is set true for today(for which I have taken a variable which stored today's day
        if((sunday && today=="sunday") || (monday && today=="monday") ||(tuesday && today=="tuesday") ||(wednesday && today=="wednesday") ||(thursday && today=="thursday") ||(friday && today=="friday") ||(saturday && today=="saturday")){
            println("Hey! It's $time \n${message ?: "Alarm ringing"}")   // printing the default message using elvish operator when message is null
        }
    }

    fun makeCoffee(){
        var monday = Coffee("monday","black",0,"7:00 AM")
        var tuesday = Coffee("tuesday","black",3,"7:15 AM")
        var wednesday = Coffee("wednesday","regular",2,"7:00 AM")
        var thursday = Coffee("thursday","black",2,"7:00 AM")
        var friday = Coffee("friday","black",2,"7:00 AM")
        var saturday = Coffee("saturday","regular",0,"8:00 AM")
        var sunday = Coffee("sunday","regular",0,"8:00 AM")
        var days = mutableListOf(monday,tuesday,wednesday,thursday,friday,saturday,sunday)

        // the coffee will be made on the basis of day
        for(day in days){
            if(day.day==today){
                println("It's ${day.time} Here's your coffee")
                if(day.sugar_teaspoon>0){
                    println("${day.typeOfCoffee} with ${day.sugar_teaspoon} teaspoon of sugar")
                }else{
                    println("${day.typeOfCoffee} with no sugar")
                }
            }
        }
    }

    fun heatWater(temperature:String,day1:String?,day2:String?){
        if(day1==null && day2==null){
            println("Your water is ready\nAs said it was $temperature degree C")
        }
        else if(day1!=null && today!=day1 || day2!=null && today!=day2){
            println("Your water is ready\nAs said it was $temperature degree C")
        }
    }

    fun packBag() {
        var monday = Books("monday","English","Physics","Chemistry","Maths","Biology")
        var tuesday = Books("tuesday","English","French","Maths","Physics","Biology")
        var wednesday = Books("wednesday","English","Maths","Chemistry","French","Lab")
        var thursday = Books("thursday","Maths","Lab","Physics","Biology","Chemistry")
        var friday = Books("friday","Maths","Lab","English","Chemistry","French")
        var days = mutableListOf(monday,tuesday,wednesday,thursday,friday)
        if(today=="saturday" || today=="sunday"){
            println("It's weekend. Take a rest today")
        }
        for(day in days){
            if(day.day==today){
                println("Bag got packed.\n${day.book1},${day.book2},${day.book3},${day.book4} and ${day.book5} books are in the bag according to your schedule")
            }
        }
    }

    fun makeFood(breakfast:String?,lunch:String?) {
        var morningFood = mutableListOf("Bread Toast","Omelette","Parantha","Fruit Salad","Poha")
        var lunchFood = mutableListOf("Fried Rice manchurian","Chole Rice","Chole with Parantha")
        if(breakfast==null){
            println("${morningFood.random()} is ready for your breakfast")
        }else{
            println("$breakfast is ready for your breakfast.")
        }
        if(lunch==null){
            println("${lunchFood.random()} is ready for your breakfast")
        }else{
            println("$breakfast is ready for your breakfast.")
        }
    }

    fun ironCloth(cloth1: String, cloth2: String) {
        println("Your $cloth1 and $cloth2 is ready to wear.\nYour other accessories are also ready")
    }

}