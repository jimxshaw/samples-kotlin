fun main(args: Array<String>) {
    // Create a string constant called firstName and initialize it to your first name.
    // Also create a string constant called lastName and initialize it to your last name.
    val firstName = "Jim"
    val lastName = "Shaw"

    // Create a string constant called fullName by adding the firstName and
    // lastName constants together, separated by a space.
    val fullName = "$firstName $lastName"

    // Using string templates, create a string constant called myDetails that uses
    // the fullName constant to create a string introducing yourself.
    // For example, it could read: "Hello, my name is Joe Howard.".
    val myDetails = "Hello, my name is $fullName"
}