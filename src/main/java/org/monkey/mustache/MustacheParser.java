// $ANTLR 3.3 Nov 30, 2010 12:50:56 Mustache.g 2011-02-14 11:37:01

package org.monkey.mustache;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class MustacheParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Data", "Lstache", "Id", "Rstache", "Hash", "Slash"
    };
    public static final int EOF=-1;
    public static final int Data=4;
    public static final int Lstache=5;
    public static final int Id=6;
    public static final int Rstache=7;
    public static final int Hash=8;
    public static final int Slash=9;

    // delegates
    // delegators


        public MustacheParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MustacheParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return MustacheParser.tokenNames; }
    public String getGrammarFileName() { return "Mustache.g"; }


    public Context context = new Context();
    public Node root = new RootNode();


    public static class toplevel_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "toplevel"
    // Mustache.g:25:1: toplevel : document EOF ;
    public final MustacheParser.toplevel_return toplevel() throws RecognitionException {
        MustacheParser.toplevel_return retval = new MustacheParser.toplevel_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF2=null;
        MustacheParser.document_return document1 = null;


        Object EOF2_tree=null;

        try {
            // Mustache.g:25:10: ( document EOF )
            // Mustache.g:25:12: document EOF
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_document_in_toplevel54);
            document1=document();

            state._fsp--;

            adaptor.addChild(root_0, document1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_toplevel56); 
            EOF2_tree = (Object)adaptor.create(EOF2);
            adaptor.addChild(root_0, EOF2_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "toplevel"

    public static class document_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "document"
    // Mustache.g:27:1: document : ( body | mustache )* ;
    public final MustacheParser.document_return document() throws RecognitionException {
        MustacheParser.document_return retval = new MustacheParser.document_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MustacheParser.body_return body3 = null;

        MustacheParser.mustache_return mustache4 = null;



        try {
            // Mustache.g:27:10: ( ( body | mustache )* )
            // Mustache.g:28:5: ( body | mustache )*
            {
            root_0 = (Object)adaptor.nil();

            // Mustache.g:28:5: ( body | mustache )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==Lstache) ) {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==Id||LA1_2==Hash) ) {
                        alt1=2;
                    }


                }
                else if ( (LA1_0==Data) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Mustache.g:28:7: body
            	    {
            	    pushFollow(FOLLOW_body_in_document72);
            	    body3=body();

            	    state._fsp--;

            	    adaptor.addChild(root_0, body3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // Mustache.g:29:7: mustache
            	    {
            	    pushFollow(FOLLOW_mustache_in_document80);
            	    mustache4=mustache();

            	    state._fsp--;

            	    adaptor.addChild(root_0, mustache4.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "document"

    public static class body_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "body"
    // Mustache.g:32:1: body : Data ;
    public final MustacheParser.body_return body() throws RecognitionException {
        MustacheParser.body_return retval = new MustacheParser.body_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Data5=null;

        Object Data5_tree=null;

        try {
            // Mustache.g:32:6: ( Data )
            // Mustache.g:32:8: Data
            {
            root_0 = (Object)adaptor.nil();

            Data5=(Token)match(input,Data,FOLLOW_Data_in_body95); 
            Data5_tree = (Object)adaptor.create(Data5);
            adaptor.addChild(root_0, Data5_tree);

             root.addChild(new DataNode((Data5!=null?Data5.getText():null))); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "body"

    public static class mustache_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mustache"
    // Mustache.g:34:1: mustache : ( interpolation | iteration );
    public final MustacheParser.mustache_return mustache() throws RecognitionException {
        MustacheParser.mustache_return retval = new MustacheParser.mustache_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        MustacheParser.interpolation_return interpolation6 = null;

        MustacheParser.iteration_return iteration7 = null;



        try {
            // Mustache.g:35:5: ( interpolation | iteration )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Lstache) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==Id) ) {
                    alt2=1;
                }
                else if ( (LA2_1==Hash) ) {
                    alt2=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Mustache.g:35:7: interpolation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interpolation_in_mustache109);
                    interpolation6=interpolation();

                    state._fsp--;

                    adaptor.addChild(root_0, interpolation6.getTree());

                    }
                    break;
                case 2 :
                    // Mustache.g:36:7: iteration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_iteration_in_mustache117);
                    iteration7=iteration();

                    state._fsp--;

                    adaptor.addChild(root_0, iteration7.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "mustache"

    public static class interpolation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interpolation"
    // Mustache.g:39:1: interpolation : Lstache Id Rstache ;
    public final MustacheParser.interpolation_return interpolation() throws RecognitionException {
        MustacheParser.interpolation_return retval = new MustacheParser.interpolation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Lstache8=null;
        Token Id9=null;
        Token Rstache10=null;

        Object Lstache8_tree=null;
        Object Id9_tree=null;
        Object Rstache10_tree=null;

        try {
            // Mustache.g:40:5: ( Lstache Id Rstache )
            // Mustache.g:40:7: Lstache Id Rstache
            {
            root_0 = (Object)adaptor.nil();

            Lstache8=(Token)match(input,Lstache,FOLLOW_Lstache_in_interpolation134); 
            Lstache8_tree = (Object)adaptor.create(Lstache8);
            adaptor.addChild(root_0, Lstache8_tree);

            Id9=(Token)match(input,Id,FOLLOW_Id_in_interpolation136); 
            Id9_tree = (Object)adaptor.create(Id9);
            adaptor.addChild(root_0, Id9_tree);

            Rstache10=(Token)match(input,Rstache,FOLLOW_Rstache_in_interpolation138); 
            Rstache10_tree = (Object)adaptor.create(Rstache10);
            adaptor.addChild(root_0, Rstache10_tree);


                    String key = (Id9!=null?Id9.getText():null);
                    // if (!context.containsKey(key)) {
                    //   throw new FailedPredicateException(
                    //     input, "context", "!context.containsKey(" + key + ")");
                    // }

                    root.addChild(new InterpolationNode(key));
                  

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "interpolation"

    public static class iteration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "iteration"
    // Mustache.g:51:1: iteration : Lstache Hash id= Id Rstache document Lstache Slash Id Rstache ;
    public final MustacheParser.iteration_return iteration() throws RecognitionException {
        MustacheParser.iteration_return retval = new MustacheParser.iteration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token Lstache11=null;
        Token Hash12=null;
        Token Rstache13=null;
        Token Lstache15=null;
        Token Slash16=null;
        Token Id17=null;
        Token Rstache18=null;
        MustacheParser.document_return document14 = null;


        Object id_tree=null;
        Object Lstache11_tree=null;
        Object Hash12_tree=null;
        Object Rstache13_tree=null;
        Object Lstache15_tree=null;
        Object Slash16_tree=null;
        Object Id17_tree=null;
        Object Rstache18_tree=null;

        try {
            // Mustache.g:52:5: ( Lstache Hash id= Id Rstache document Lstache Slash Id Rstache )
            // Mustache.g:52:7: Lstache Hash id= Id Rstache document Lstache Slash Id Rstache
            {
            root_0 = (Object)adaptor.nil();

            Lstache11=(Token)match(input,Lstache,FOLLOW_Lstache_in_iteration157); 
            Lstache11_tree = (Object)adaptor.create(Lstache11);
            adaptor.addChild(root_0, Lstache11_tree);

            Hash12=(Token)match(input,Hash,FOLLOW_Hash_in_iteration159); 
            Hash12_tree = (Object)adaptor.create(Hash12);
            adaptor.addChild(root_0, Hash12_tree);

            id=(Token)match(input,Id,FOLLOW_Id_in_iteration163); 
            id_tree = (Object)adaptor.create(id);
            adaptor.addChild(root_0, id_tree);

            Rstache13=(Token)match(input,Rstache,FOLLOW_Rstache_in_iteration165); 
            Rstache13_tree = (Object)adaptor.create(Rstache13);
            adaptor.addChild(root_0, Rstache13_tree);


                    Node oldRoot = root;
                    root = new IterationNode((id!=null?id.getText():null));
                  
            pushFollow(FOLLOW_document_in_iteration169);
            document14=document();

            state._fsp--;

            adaptor.addChild(root_0, document14.getTree());
            Lstache15=(Token)match(input,Lstache,FOLLOW_Lstache_in_iteration171); 
            Lstache15_tree = (Object)adaptor.create(Lstache15);
            adaptor.addChild(root_0, Lstache15_tree);

            Slash16=(Token)match(input,Slash,FOLLOW_Slash_in_iteration173); 
            Slash16_tree = (Object)adaptor.create(Slash16);
            adaptor.addChild(root_0, Slash16_tree);

            Id17=(Token)match(input,Id,FOLLOW_Id_in_iteration175); 
            Id17_tree = (Object)adaptor.create(Id17);
            adaptor.addChild(root_0, Id17_tree);

            Rstache18=(Token)match(input,Rstache,FOLLOW_Rstache_in_iteration177); 
            Rstache18_tree = (Object)adaptor.create(Rstache18);
            adaptor.addChild(root_0, Rstache18_tree);


                    oldRoot.addChild(root);
                    root = oldRoot;
                  

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "iteration"

    // Delegated rules


 

    public static final BitSet FOLLOW_document_in_toplevel54 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_toplevel56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_body_in_document72 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_mustache_in_document80 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_Data_in_body95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interpolation_in_mustache109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iteration_in_mustache117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Lstache_in_interpolation134 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Id_in_interpolation136 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Rstache_in_interpolation138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Lstache_in_iteration157 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_Hash_in_iteration159 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Id_in_iteration163 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Rstache_in_iteration165 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_document_in_iteration169 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Lstache_in_iteration171 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_Slash_in_iteration173 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_Id_in_iteration175 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Rstache_in_iteration177 = new BitSet(new long[]{0x0000000000000002L});

}