# semantic-search-starters

use gradlew and run

./gradlew bootRun

to start the application. After that you are able to use the services:

# Understand
Use this service to analyze a give query and get the analyzed query as a result.
http://localhost:8080/process?phrase=Grabe%20dein%20Grab%20du%20Riesensack.

# External libraries
I used https://github.com/lucidworks/auto-phrase-tokenfilter as multiple word synonyms e.g. "Herr der Ringe"
A high level documentation on auto-phrase-tokenfilter may be found here
https://lucidworks.com/blog/2014/07/02/automatic-phrase-tokenization-improving-lucene-search-precision-by-more-precise-linguistic-analysis/
