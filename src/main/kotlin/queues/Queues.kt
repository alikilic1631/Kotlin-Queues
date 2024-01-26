package queues

import java.util.PriorityQueue

interface Queue<T> {

    fun enqueue(elem: T)

    fun peek(): T?

    fun dequeue(): T

    fun isEmpty(): Boolean
    fun size(): Int
}

class FifoQueue<T> : Queue<T> {
    private var queue: MutableList<T> = mutableListOf()

    override fun enqueue(elem: T) {
        queue.add(elem)
    }

    override fun peek(): T? {
        return if (isEmpty()) {
            null
        } else {
            queue[0]
        }
    }

    override fun dequeue(): T {
        val dropped: T = queue[0]
        queue.removeAt(0)
        return dropped
    }

    override fun size(): Int = queue.size

    override fun isEmpty(): Boolean {
        return queue.size != 0
    }
}

class LifoQueue<T> : Queue<T> {
    private var queue: MutableList<T> = mutableListOf()
    override fun enqueue(elem: T) {
        queue.add(elem)
    }

    override fun peek(): T? {
        return if (isEmpty()) {
            null
        } else {
            queue[queue.lastIndex]
        }
    }

    override fun dequeue(): T {
        val dropped: T = queue[queue.lastIndex]
        queue.removeAt(queue.lastIndex)
        return dropped
    }

    override fun size(): Int = queue.size

    override fun isEmpty(): Boolean {
        return queue.size != 0
    }
}

class PrQueue<T>(comp: Comparator<T>? = null) : Queue<T> {

    private var queue = PriorityQueue(comp)
    override fun enqueue(elem: T) {
        queue.add(elem)
    }


    override fun peek(): T? {
        return if (isEmpty()) {
            null
        } else {
            queue.first()
        }
    }

    override fun dequeue(): T {
        val dropped: T = queue.first()
        queue.remove()
        return dropped
    }

    override fun size(): Int = queue.size
    override fun isEmpty(): Boolean {
        return queue.size != 0
    }
}


