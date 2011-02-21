grammar Mustache;

options {
  output = AST;
}

@header {
package org.monkey.mustache;
import java.util.HashMap;
}

@members {
public Context context = new Context();
public Node root = new RootNode();
}

@lexer::header {
package org.monkey.mustache;
}

@lexer::members {
boolean inTag = false;
}

toplevel : document EOF ;

document : 
    ( body
    | mustache
    )*;

body : Data { root.addChild(new DataNode($Data.text)); };

mustache
    : interpolation
    | iteration
    ;

interpolation
    : Lstache Id Rstache {
        String key = $Id.text;
        // if (!context.containsKey(key)) {
        //   throw new FailedPredicateException(
        //     input, "context", "!context.containsKey(" + key + ")");
        // }

        root.addChild(new InterpolationNode(key));
      }
    ;

iteration
    : Lstache Hash id=Id Rstache {
        Node oldRoot = root;
        root = new IterationNode($id.text);
      } document Lstache Slash Id Rstache {
        oldRoot.addChild(root);
        root = oldRoot;
      }
    ; 

Lstache : '{{' { inTag = true; } ;
Rstache : '}}' { inTag = false; } ;
Id : { inTag }?=> ('a'..'z' | 'A'..'Z' | '_' | '.')+ ;
Slash : { inTag }?=> '/' ;
Hash : { inTag }?=> '#' ;
Data : { !inTag }?=> (~'{')+ ;
