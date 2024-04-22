# File-compression-and-decompression
Huffman encoding greedy technique algorithm

The above project is a Java implementation of Huffman coding for compression and decompression. Here's a breakdown of its components and functionality:

1. **HuffmanNode Class**: Defines a node structure for building the Huffman tree. Each node contains a character (for leaf nodes), frequency count, and references to left and right children nodes.

2. MyComparator Class: Implements a custom comparator for prioritizing nodes in the Huffman tree based on their frequency counts.

3. Main Class (HuffmanCompression):
   - Calculates the frequency of each character in the input string using a HashMap.
   - Constructs a priority queue (min-heap) of Huffman nodes based on character frequencies.
   - Builds the Huffman tree by repeatedly combining the two lowest frequency nodes until only one node (the root) remains.
   - Prints the Huffman codes for each character in the tree.
   - Encodes the input string using the generated Huffman codes to produce a compressed string.
   - Decodes the compressed string back to its original form using the Huffman tree.
   - Demonstrates both compression and decompression processes.

4. GetCode Method: Recursively traverses the Huffman tree to find the Huffman code for a given character.

The project illustrates the fundamental concepts of Huffman coding, a widely used algorithm for lossless data compression. It showcases the process of constructing a Huffman tree based on character frequencies, generating Huffman codes for each character, and using these codes for compression and subsequent decompression of data. This implementation serves as a foundation for building more advanced compression algorithms and applications.
