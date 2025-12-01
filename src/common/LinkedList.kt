package common

import java.util.UUID

class Node(
    var value: String,
    var next: Node? = null,
    var prev: Node? = null,
    var subList: LinkedList? = null){
}

class LinkedList {

    fun getSize(): Long {
        var counter = 0L
        var current = head
        while (current != null) {
            counter++ // Conta il nodo corrente
            if (current.subList != null) {
                counter += current.subList!!.getSize() // Conta la sotto-lista
            }
            current = current.next
        }
        return counter
    }

    var head: Node? = null

    fun add(value: String) {
        if (head == null) {
            head = Node(value)
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(value)
        }
    }

    fun split(): LinkedList {
        val secondList = LinkedList()
        if (head == null) return secondList

        var length = 0
        var current = head
        while (current != null) {
            length++
            current = current.next
        }

        val mid = length / 2
        var index = 0
        current = head

        while (current != null) {
            if (index == mid - 1) {
                secondList.head = current.next
                current.next = null
                break
            }
            current = current.next
            index++
        }
        return secondList
    }

    override fun toString(): String {
        val values = mutableListOf<String>()
        var current = head
        while (current != null) {
            values.add(current.value)
            current = current.next
        }
        return values.joinToString(" -> ")
    }
}