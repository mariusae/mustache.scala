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
    | section
    | invertedSection
    | comment
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

section
    : LL Hash id=Id RR {
        Node oldRoot = root;
        root = nodeFactory.newSectionNode($id.text);
      } document LL Slash Id RR {
        // TODO: check ids.
        oldRoot.addChild(root);
        root = oldRoot;
      }
    ; 

invertedSection
    : LL Hat id=Id RR {
        Node oldRoot = root;
        root = nodeFactory.newInvertedSectionNode($id.text);
      } document LL Slash Id RR {
        // TODO: check ids.
        oldRoot.addChild(root);
        root = oldRoot;
      }
    ; 

comment
	: LL Bang .* RR  // Ignore.
    ;

LLL : '{{{' { inTag = true; } ;
RRR : '}}}' { inTag = false; } ;
LL : '{{' { inTag = true; } ;
RR : '}}' { inTag = false; } ;
Id    : { inTag }?=> ('a'..'z' | 'A'..'Z' | '_' | '.' | '1'..'2')+ ;
Slash : { inTag }?=> '/' ;
Hash  : { inTag }?=> '#' ;
Amp	  : { inTag }?=> '&' ;
Hat	  : { inTag }?=> '^' ;
Bang  : { inTag }?=> '!' ;
Ws	  : { inTag }?=> ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+
        { $channel = HIDDEN; };

Data  : { !inTag }?=> (~'{')+ ;
