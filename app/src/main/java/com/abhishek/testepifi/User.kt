package com.abhishek.testepifi

class User(var panNo: String, var birthDate: String) {

    /*
    * Functions to validate the entered pan number and birthdate
    * */
    fun isPanEmpty() : Boolean{
        return (panNo == "null")
    }

    fun isBirthDateEmpty() : Boolean{
        return birthDate.split("/")[0] == "null"
    }
    fun isBirthMonthEmpty() : Boolean{
        return birthDate.split("/")[1] == "null"
    }
    fun isBirthYearEmpty() : Boolean{
        return birthDate.split("/")[2] == "null"
    }

    fun isValidBirthDate(): Boolean {
        val currDate = this.birthDate

        val splitDate = currDate.split("/")
        val date = splitDate[0].toInt()
        val month = splitDate[1].toInt()
        val year = splitDate[2].toInt()

        if( (date <= 0 || date > 31 || month <= 0 || month > 12 || year <= 1921 || year > 2021) ||
            (month == 2 && date > 28)
                ){
            return false
        }

        if( (month == 4 || month == 6 || month == 9 || month == 11 ) && date >30){
            return false
        }

        return true
    }

    fun isValidPanNumber() : Boolean{

        // verification done of basis on format of PAN Number
        //format -> The PAN structure is as follows: Fourth character [P — Individual or Person ] Example: AAAPZ1234C
        //
        //The first five characters are letters (in uppercase by default), followed by four numerals, and the last (tenth) character is a letter.
        //The first three characters of the code are three letters forming a sequence of alphabets letters from AAA to ZZZ

        // source -> https://en.wikipedia.org/wiki/Permanent_account_number

        if(this.panNo.length != 10){
            return false;
        }
        val string = this.panNo;
        for(i in 0..4){
            if(string[i].isDigit() || string[i].isLowerCase()){
                return false;
            }
        }

        for(i in 5..8){
            if(!string[i].isDigit()){
                return false;
            }
        }

        if(string[string.length - 1].isDigit()){
            return false;
        }
        return true;
    }
}