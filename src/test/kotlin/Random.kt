object Random {

    fun randomLong(min: Long, max: Long) = (Math.random() * (max - min)).toLong() + min
}