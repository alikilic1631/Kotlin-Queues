package queues

interface Acceptor<T> {
    fun accept(elem: T) {}
}

interface Forwarder {
    fun forward() {

    }
}

class QueueNode<T>(
    private val queue: Queue<T>,
    private val successor: Acceptor<T>
) : Acceptor<T>, Forwarder {

    override fun forward() {
        val nextElem = queue.dequeue()
        successor.accept(nextElem)
    }

    override fun accept(elem: T) {
        queue.enqueue(elem)
    }

}

class Sink<T> : Acceptor<T> {
    private var list: MutableList<T> = mutableListOf()

    override fun accept(elem: T) {
        list.add(elem)
    }

    fun getAccepted(): MutableList<T> {
        return list
    }
}