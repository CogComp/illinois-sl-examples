Examples to accompany the illinois-sl package.

To quickly test all solvers, simply run

```
mvn dependency:copy-dependencies
mvn compile
sh scripts/testAllSolvers.sh 
```

We illustrate how to use illinois-sl's interface for 3 popular
learning applications -- cost-sensitive multi-class classification,
reranking, and sequential tagging. The following scripts are provided
to run these examples from the command line.

- scripts/run_tutorial.sh
- scripts/run_sequence.sh
- scripts/run_multiclass.sh
- scripts/run_reranking.sh

The sample data sets can be found at 'data' directory. 
We describe the usage of each script below. 

POS Tagging
===========

To train a part of speech tagger model named `posModel` on
'data/tutorial/big.train' with DEMIParallelDCD solver, run:

```
> ./scripts/run_tutorial.sh trainPOSModel data/tutorial/big.train  config/DEMIParallelDCD.config posModel
```

The following script evaluates the performance of `posModel` on 
`data/tutorial/big.test`:

```
>./scripts/run_tutorial.sh testPOSModel posModel data/tutorial/big.test
```

Sequence Tagging
================
scripts/run_sequence.sh
____


Use the following comment to train a sequential model 'seqModel' on 
'data/tutorial/wsj.sub.train' with DEMIParallelDCD solver:

```
> ./scripts/run_sequence.sh trainSequenceModel data/sequence/wsj.sub.train config/DEMIParallelDCD.config seqModel
```
Again, one can use another structured learning approach to train the model 
by specifying a different config file. Each line follows the following format 

```
[Tag] qid:[example_id] [feature1_index]:[feature1_value] [feature2_index]:[feature2_value] ...
```

`[Tag]` is a positive integer representing the label. All lines with the same `[example_id]` belong to the same structured example. 

The following comment tests 'seqModel" on 'data/sequence/wsj.sub.test' data set.
```
> ./scripts/run_sequence.sh testSequenceModel seqModel data/sequence/wsj.sub.test
```

4.1.2 __ scripts/run_multiclass.sh __

Use the following comment to train a multiclass model 'multiModel' on 
'data/multiclass/heart_scale.train' data with a cost matrix specified in 
'data/multiclass/heart_scale.cost' using DEMIParallelDCD solver:

```
> ./scripts/run_multiclass.sh trainMultiClassModel data/multiclass/heart_scale.train data/multiclass/heart_scale.cost config/DEMIParallelDCD.config multiModel
```
Each line of the input data represents one instance, and it follows the following format:

```
[Tag] [feature1_index]:[feature1_value] [feature2_index]:[feature2_value] ...
```
where `[Tag]` is an integer representing label.

The cost matrix file specifies the loss of  wrong predictions.
Each line follows the following format:

```
[gold_label] [predicted_label] [cost]
```
The cost needs to be positive, and it is 0 when `[gold_label]=[predicted_label]`.

To test the performance of 'multiModel' on 'data/multiclass/heart_scale.test', use

```
> ./scripts/run_multiclass.sh testMultiClassModel multiModel data/multiclass/heart_scale.test
```

scripts/run_reranking.sh
____


Use the following comment to train a re-ranker on 'data/reranking/rank.train' with  Strctured Perceptron

```
> ./scripts/run_reranking.sh trainRankingModel data/reranking/rerank.train config/StructuredPerceptron.config  rankModel
```
Use the following comment to test the re-ranker model on 'data/reranking/rerank.test'
```
> ./scripts/run_reranking.sh testRankingModel rankModel data/reranking/rerank.test
```
These scripts should be runnable on installation; However, it is possible, 
depending on your system configuration, that you will first need to modify
the permissions on the scripts to allow you to execute them:

```
> chmod 744 scripts/*sh
```


Config
======
One can use other structured learning solvers by simply specifying corresponding config file in 'config/'

Currently, we provide the following options:
- `DCD.config`: a dual coordinate descent approach (single thread) for Structured SVM.
- `DEMIParallelDCD.config`: DEMIDCD for Structured SVM. (see the ECML paper below)
- `ParallelDCD.config`: a master-slave dual coordinate descent method for Structured SVM.
- `StrcturedPerceptron.config`: a Structured Perceptron implementation.

