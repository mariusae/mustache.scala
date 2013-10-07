# mustache.scala

`mustache.scala` is an implementation of
[mustache](http://mustache.github.com/) for Scala. It does not at
present support custom delimiters.

To render a template, a `Mustache` object must first be
instantiated. This takes a `scala.io.Source`, which has many
convenience methods for reading files, strings, etc.

    import org.monkey.mustache._
    val mustache = new Mustache(io.Source.fromString(
      "{{helloworld}} {{#truefalse}}yes?{{/truefalse}}" + 
      "{{#falsetrue}}grr{{/falsetrue}}" +
      "{{#section}}{{v2}}{{/section}}"))
    
Once the template is instantiated, it is rendered with a
`Dictionary`. `Dictionary` provides a builder to create dictionaries:

    val d = Dictionary()
      .data("helloworld", "hello, world!")
      .bool("truefalse", true)
      .dictionary(
        "section",
        Dictionary()
          .data("v2", "iter0"))
      .dictionary(
        "section",
        Dictionary()
          .data("v2", "iter1"))

The string is then rendered with `Mustache.apply`
        
    scala> mustache(d)
    res4: String = hello, world! yes?iter0iter1
    
The mustache AST is available to the application developer, so
she may provider her own interpreter. This is useful, for example, 
to give the template access to an object graph directly instead
of the default `Dictionary` implementation.

# maven / sbt

`mustache.scala` is published to the maven central repo.  To use with sbt,
add the following lines:

    val mustache = "org.monkey" % "mustache" % "1.0.4-SNAPSHOT"

