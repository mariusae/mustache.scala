grammar Mustache;

options {
  output = AST;
}

@header {
package org.monkey.mustache;
import java.util.HashMap;
}

@members {
// Filled in by caller?
public NodeFactory nodeFactory;
public Node root;
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

body : Data { root.addChild(nodeFactory.newDataNode($Data.text)); };

mustache
    : interpolation
    | unescapedInterpolation
    | iteration
    ;

unescapedInterpolation
    : LLL Id RRR {
        root.addChild(nodeFactory.newUnescapedInterpolationNode($Id.text));
      }
    ;


interpolation
    : LL Id RR {
        root.addChild(nodeFactory.newInterpolationNode($Id.text));
      }
    | LL Amp Id RR {
        root.addChild(nodeFactory.newUnescapedInterpolationNode($Id.text));
      }
    ;

iteration
    : LL Hash id=Id RR {
        Node oldRoot = root;
        root = nodeFactory.newIterationNode($id.text);
      } document LL Slash Id RR {
        oldRoot.addChild(root);
        root = oldRoot;
      }
    ; 

LLL : '{{{' { inTag = true; } ;
RRR : '}}}' { inTag = false; } ;
LL : '{{' { inTag = true; } ;
RR : '}}' { inTag = false; } ;
Id    : { inTag }?=> ('a'..'z' | 'A'..'Z' | '_' | '.')+ ;
Slash : { inTag }?=> '/' ;
Hash  : { inTag }?=> '#' ;
Amp	  : { inTag }?=> '&' ;
Ws	  : { inTag }?=> ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+
        { $channel = HIDDEN; };
Data  : { !inTag }?=> (~'{')+ ;
