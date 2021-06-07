# Java-Ngram-Analyzer-Tool
Given a text document and a **"n"** value, program
extracts all n-gram features of the text document. N-gram is a contiguous sequence of **n"** words from a given sample of text or speech.
Program use HashMap data structure to hold n-gram features and their count value. It exclusively compute frequency for each n-gram features. 
The output sorts by count value descendingly.
### Sample input:
* A n value can be any positive integer. The output file is a CSV (comma-separated values) formatted file.
* Suppose that input.txt has a following text:
  * You are expected to develop a text analyzer tool. The program allows you to find the mostfrequent phrases and frequencies of words. Non-English language texts are supported.It also counts number of words, characters, sentences and syllables. Also calculates lexical density.
* The command **"java NgramExtractor input.txt output.csv 1"** must extract all 1-gram features of text document to the output.csv file
* The command **"java NgramExtractor input.txt output.csv 2"** must extract all 2-gram features of text document
### Run

**./java NgramExtractor input.txt output.csv n**
