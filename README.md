# Fork of [Http Request](https://github.com/kevinsawicki/http-request)

## Daikon
[Daikon](https://plse.cs.washington.edu/daikon/faq.html) is an invariant detector. It uses machine learning to look at
the runtime properties of some code and find things that are "true" about the data. For example, it can tell us that
when `HttpRequest.get` is run, `return.requestMethod.toString` will always be `"GET"`. I'm not yet certain in what
capacity we can use this to help solve our specific problem, but it may be useful depending on our approach.

Running with Daikon:
TODO: Can this use the actual tests / how to get Maven to run tests w/Daikon?
```bash
# first, download Daikon and follow setup instructions
# compile the test Main file test.main.Main
javac -cp lib/src/main/java -d compiled-test lib/src/main/java/test/main/Main.java
# create the DynComp file
java -cp compiled-test:$DAIKONDIR/daikon.jar daikon.DynComp  test.main.Main
# now, run with Daikon. This may take >30s
java -cp compiled-test:$DAIKONDIR/daikon.jar daikon.Chicory --comparability-file=Main.decls-DynComp --daikon test.main.Main
```
This produces a `Main.inv.gz` file that is a serialized version of the invariants found by Daikon.
The command also sends a stringified version to stdout. Here is a snippet:
```
===========================================================================
com.github.kevinsawicki.http.HttpRequest$8:::OBJECT
this.val$input.getClass().getName() == this.closeable.getClass().getName()
com.github.kevinsawicki.http.HttpRequest.METHOD_GET == com.github.kevinsawicki.http.HttpRequest.this.requestMethod
com.github.kevinsawicki.http.HttpRequest.this.ignoreCloseExceptions == this.ignoreCloseExceptions
this has only one value
this.val$input has only one value
this.val$input.getClass().getName() == java.io.BufferedInputStream.class
this.val$output has only one value
this.val$output.getClass().getName() == java.io.ByteArrayOutputStream.class
com.github.kevinsawicki.http.HttpRequest.this has only one value
com.github.kevinsawicki.http.HttpRequest.CHARSET_UTF8 has only one value
com.github.kevinsawicki.http.HttpRequest.CHARSET_UTF8.toString == "UTF-8"
com.github.kevinsawicki.http.HttpRequest.CONTENT_TYPE_FORM has only one value
com.github.kevinsawicki.http.HttpRequest.CONTENT_TYPE_FORM.toString == "application/x-www-form-urlencoded"
com.github.kevinsawicki.http.HttpRequest.CONTENT_TYPE_JSON has only one value
com.github.kevinsawicki.http.HttpRequest.CONTENT_TYPE_JSON.toString == "application/json"
...
```
[Link to full output](https://gist.github.com/UltimatePro-Grammer/b81d9b9d9074981d6872684c880dd414)

## JavaMOP

TODO

