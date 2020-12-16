**FamilyTree**

This class takes an input string and return a single output string result that represents the data as a hierarchy (root, children, siblings, etc), with each person on a separate line.

The given string has PIPE(|) delimited nodes that represent family members in a family tree. Each node is a CSV with the values being "parent_id, node_id, node_name".

**Sample input:**<br />
"null,0,grandpa|0,1,son|0,2,daughter|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid"

**Note**<br />
If not string argument is given, the sample input above is used as a default.

The hierarchical result is displayed on a separate line with each person(with id) in the tree, with it's parent id

**Sample output:**<br />
person( 0 ): grandpa with parent(no parent given)<br />
person( 1 ): son with parent(grandpa)<br />
person( 3 ): grandkid with parent(son)<br />
person( 4 ): grandkid with parent(son)<br />
person( 2 ): daughter with parent(grandpa)<br />
person( 5 ): grandkid with parent(daughter)<br />
person( 6 ): greatgrandkid with parent(grandkid)<br />

**To install kotlin:**<br />
  curl -s "https://get.sdkman.io" | bash<br />
  source "$HOME/.sdkman/bin/sdkman-init.sh"<br />
  sdk version<br />
  sdk install kotlin<br />

**To Build Jar:**<br />
  kotlinc src/main/kotlin/main.kt -include-runtime -d family-tree.jar

**To Run tests**<br />
gradle test or ./gradlew test

**Usage**<br />
java -jar family-tree.jar "null,0,grandpa|0,1,son|0,2,daughter|1,3,grandkid|1,4,grandkid"<br />

**Outputs**<br />
person( 0 ): grandpa with parent(no parent given)
person( 1 ): son with parent(grandpa)<br />
person( 3 ): grandkid with parent(son)<br />
person( 4 ): grandkid with parent(son)<br />
person( 2 ): daughter with parent(grandpa)<br />
person(2): daughter with parent(0)<br />

 