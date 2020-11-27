import java.util.*


// "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid"

fun main(args: Array<String>) {
    val input = if (!args.isEmpty() && args.size > 0) args[0] else {
        "null,0,grandpa|0,1,son|0,2,daughter|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid"
    }
    val instance = FamilyTree(input)
    instance.loadFamilyTree()
    val output = instance.traverseTree(instance.root)
    println(output)
}

data class FamilyTree(val input: String) {
    public var inputStr: String = input
    public var root: Node = Node("null",-1,"null")

    fun traverseTree(rootNode: Node): String {
        var output:String = ""
        if (rootNode.id != -1L) {
            output += "person("+rootNode.id.toString() + "): " + rootNode.name + " with parent(" + rootNode.parent_id + ")\n"
        }
        if (rootNode.children.size != 0) for (ch in rootNode.children) output += traverseTree(ch)
        return output
    }
    public fun parseNodesFromInput(): List<String> = input.split("|")
    public fun loadFamilyTree() {
        val nodes = this.parseNodesFromInput()
        nodes.forEach {this.addNodeToTree(this.root, this.parseNodeInfo(it))}
    }
    public fun convertToLong(s: String) = try {if (s == "null") -1L else s.toLong()} catch (ex: NumberFormatException){-1L}
    public fun parseNodeInfo(info: String): Node {
        val parts: List<String> = info.split(",").map { it -> it.trim() }
        if (parts.size == 3) { return Node(parts[0], convertToLong(parts[1]), parts[2]) }
        return Node("null",-1,"null")
    }
    public fun addNodeToTree(rootNode: Node, node: Node) {
        if (node.parent_id == "null") {
            this.root = node;
            this.root.parent = null;
        } else if (convertToLong(node.parent_id) != rootNode.id ) {
            if (rootNode.children.size != 0) for (ch in rootNode.children) addNodeToTree(ch,node)
        }
        else rootNode.addChild(node)
    }
    class Node(val parent_id: String, private val node_id: Long, private val node_name: String) {
        var parent : Node? = null
        var children: MutableList<Node> = ArrayList()
        var name : String = node_name
        var id : Long = node_id

        fun addChild(childNode: Node) {
            childNode.parent=this
            this.children.add(childNode)
        }

        init {
            name = node_name
        }
    }
}

