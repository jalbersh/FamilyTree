import org.junit.Test
import org.junit.Assert

class TestFamilyTree {

    @Test
    fun whenNull_convertReturnsMinus1() {
        val familyTree = FamilyTree("1|2|3")
        Assert.assertEquals(null,familyTree.convertToLong(("null")))
    }

    @Test
    fun whenLong_convertReturns_long() {
        val familyTree = FamilyTree("0,1,2")
        Assert.assertEquals(3L,familyTree.convertToLong("3"))
    }

    @Test
    fun whenSimpleInput_give_3SimpleNodes() {
        val familyTree = FamilyTree("1|2|3")
        val nodes = familyTree.parseNodesFromInput()
        Assert.assertEquals(3,  nodes.size)
        Assert.assertEquals("2",nodes[1])
    }

    @Test
    fun whenSimpleInput_give_simpleNode() {
        val familyTree = FamilyTree("null,2,daughter")
        val nodes = familyTree.parseNodesFromInput()
        Assert.assertEquals(1,  nodes.size)
        val node = familyTree.parseNodeInfo(nodes.get(0))
        Assert.assertEquals(null,node.parent_id)
        Assert.assertEquals(2L,node.id)
        Assert.assertEquals("daughter", node.name)
    }

    @Test
    fun whenAdd1Node_to_tree_confirms_1_node_in_tree() {
        val familyTree = FamilyTree("null,2,daughter")
        val nodes = familyTree.parseNodesFromInput()
        Assert.assertEquals(1,  nodes.size)
        val node = familyTree.parseNodeInfo(nodes.get(0))
        familyTree.addNodeToTree(familyTree.root, node)
        Assert.assertNotNull(familyTree.root)
        Assert.assertNotNull(familyTree.root.children)
        Assert.assertEquals(0,familyTree.root.children.size)
        Assert.assertEquals("daughter",familyTree.root.name)
    }

    @Test
    fun when_addingParentAndSon_confirm_2_nodes() {
        val familyTree = FamilyTree("null,0,father|0,1,son")
        familyTree.loadFamilyTree()
        Assert.assertEquals(1,familyTree.root.children.size)
        Assert.assertEquals("father",familyTree.root.name)
        Assert.assertEquals("son",familyTree.root.children.get(0).name)
    }

    @Test
    fun when_traversingTree_confirmOutput() {
        val familyTree = FamilyTree("null,0,father|0,1,son")
        familyTree.loadFamilyTree()
        val output = familyTree.traverseTree(familyTree.root)
        println(output)
        Assert.assertTrue(output.contains("person(0): father with parent(null)"))
        Assert.assertTrue(output.contains("person(1): son with parent(0"))
    }

}
