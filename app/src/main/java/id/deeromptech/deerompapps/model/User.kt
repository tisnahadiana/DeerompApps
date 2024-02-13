package id.deeromptech.deerompapps.model

data class User(
    var username : String? = null,
    var password : Int? = null
)

var dataDummy = listOf(
    User (
        username = "tisnahadiana",
        password = 12345
    )
)