
interface SendMessage {
    fun send()
}

data class SmsMessage(
    private val phoneNumber: String,
    private val content: String) :SendMessage {

    override fun send() {
        // send sms here
    }
}

data class EmailMessage(
    private val emailAddress: String,
    private val content: String) :SendMessage {

    override fun send() {
        // send email here
    }
}

fun main() {

    val sendMessage :SendMessage = SmsMessage(
        "09360000000" , "hello")

    sendMessage.send()

}

