// $ANTLR 3.3 Nov 30, 2010 12:50:56 Mustache.g 2011-02-14 11:37:01

package org.monkey.mustache;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MustacheLexer extends Lexer {
    public static final int EOF=-1;
    public static final int Data=4;
    public static final int Lstache=5;
    public static final int Id=6;
    public static final int Rstache=7;
    public static final int Hash=8;
    public static final int Slash=9;

    boolean inTag = false;


    // delegates
    // delegators

    public MustacheLexer() {;} 
    public MustacheLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MustacheLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Mustache.g"; }

    // $ANTLR start "Lstache"
    public final void mLstache() throws RecognitionException {
        try {
            int _type = Lstache;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:61:9: ( '{{' )
            // Mustache.g:61:11: '{{'
            {
            match("{{"); 

             inTag = true; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Lstache"

    // $ANTLR start "Rstache"
    public final void mRstache() throws RecognitionException {
        try {
            int _type = Rstache;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:62:9: ( '}}' )
            // Mustache.g:62:11: '}}'
            {
            match("}}"); 

             inTag = false; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Rstache"

    // $ANTLR start "Id"
    public final void mId() throws RecognitionException {
        try {
            int _type = Id;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:63:4: ({...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' )+ )
            // Mustache.g:63:6: {...}? => ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' )+
            {
            if ( !(( inTag )) ) {
                throw new FailedPredicateException(input, "Id", " inTag ");
            }
            // Mustache.g:63:19: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='.'||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Mustache.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Id"

    // $ANTLR start "Slash"
    public final void mSlash() throws RecognitionException {
        try {
            int _type = Slash;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:64:7: ({...}? => '/' )
            // Mustache.g:64:9: {...}? => '/'
            {
            if ( !(( inTag )) ) {
                throw new FailedPredicateException(input, "Slash", " inTag ");
            }
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Slash"

    // $ANTLR start "Hash"
    public final void mHash() throws RecognitionException {
        try {
            int _type = Hash;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:65:6: ({...}? => '#' )
            // Mustache.g:65:8: {...}? => '#'
            {
            if ( !(( inTag )) ) {
                throw new FailedPredicateException(input, "Hash", " inTag ");
            }
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Hash"

    // $ANTLR start "Data"
    public final void mData() throws RecognitionException {
        try {
            int _type = Data;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Mustache.g:66:6: ({...}? => (~ '{' )+ )
            // Mustache.g:66:8: {...}? => (~ '{' )+
            {
            if ( !(( !inTag )) ) {
                throw new FailedPredicateException(input, "Data", " !inTag ");
            }
            // Mustache.g:66:22: (~ '{' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\u0000' && LA2_0<='z')||(LA2_0>='|' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Mustache.g:66:23: ~ '{'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='z')||(input.LA(1)>='|' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Data"

    public void mTokens() throws RecognitionException {
        // Mustache.g:1:8: ( Lstache | Rstache | Id | Slash | Hash | Data )
        int alt3=6;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // Mustache.g:1:10: Lstache
                {
                mLstache(); 

                }
                break;
            case 2 :
                // Mustache.g:1:18: Rstache
                {
                mRstache(); 

                }
                break;
            case 3 :
                // Mustache.g:1:26: Id
                {
                mId(); 

                }
                break;
            case 4 :
                // Mustache.g:1:29: Slash
                {
                mSlash(); 

                }
                break;
            case 5 :
                // Mustache.g:1:35: Hash
                {
                mHash(); 

                }
                break;
            case 6 :
                // Mustache.g:1:40: Data
                {
                mData(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\2\uffff\1\6\1\10\1\11\1\12\1\uffff\1\13\10\uffff";
    static final String DFA3_eofS =
        "\20\uffff";
    static final String DFA3_minS =
        "\1\0\1\uffff\1\175\3\0\1\uffff\5\0\4\uffff";
    static final String DFA3_maxS =
        "\1\uffff\1\uffff\1\175\3\uffff\1\uffff\1\uffff\4\0\4\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\4\uffff\1\6\5\uffff\1\3\1\4\1\5\1\2";
    static final String DFA3_specialS =
        "\1\10\1\uffff\1\0\1\6\1\4\1\11\1\uffff\1\1\1\2\1\7\1\5\1\3\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\43\6\1\5\12\6\1\3\1\4\21\6\32\3\4\6\1\3\1\6\32\3\1\1\1\6\1"+
            "\2\uff82\6",
            "",
            "\1\7",
            "\56\6\1\3\22\6\32\3\4\6\1\3\1\6\32\3\1\uffff\uff84\6",
            "\173\6\1\uffff\uff84\6",
            "\173\6\1\uffff\uff84\6",
            "",
            "\173\6\1\uffff\uff84\6",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( Lstache | Rstache | Id | Slash | Hash | Data );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_2 = input.LA(1);

                         
                        int index3_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA3_2=='}') ) {s = 7;}

                        else s = 6;

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA3_7>='\u0000' && LA3_7<='z')||(LA3_7>='|' && LA3_7<='\uFFFF')) && (( !inTag ))) {s = 6;}

                        else s = 11;

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inTag )) ) {s = 12;}

                        else if ( (( !inTag )) ) {s = 6;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_11 = input.LA(1);

                         
                        int index3_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (!((( !inTag )))) ) {s = 15;}

                        else if ( (( !inTag )) ) {s = 6;}

                         
                        input.seek(index3_11);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA3_4>='\u0000' && LA3_4<='z')||(LA3_4>='|' && LA3_4<='\uFFFF')) && (( !inTag ))) {s = 6;}

                        else s = 9;

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_10 = input.LA(1);

                         
                        int index3_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inTag )) ) {s = 14;}

                        else if ( (( !inTag )) ) {s = 6;}

                         
                        input.seek(index3_10);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA3_3=='.'||(LA3_3>='A' && LA3_3<='Z')||LA3_3=='_'||(LA3_3>='a' && LA3_3<='z')) && ((( inTag )||( !inTag )))) {s = 3;}

                        else if ( ((LA3_3>='\u0000' && LA3_3<='-')||(LA3_3>='/' && LA3_3<='@')||(LA3_3>='[' && LA3_3<='^')||LA3_3=='`'||(LA3_3>='|' && LA3_3<='\uFFFF')) && (( !inTag ))) {s = 6;}

                        else s = 8;

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA3_9 = input.LA(1);

                         
                        int index3_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inTag )) ) {s = 13;}

                        else if ( (( !inTag )) ) {s = 6;}

                         
                        input.seek(index3_9);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA3_0 = input.LA(1);

                         
                        int index3_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA3_0=='{') ) {s = 1;}

                        else if ( (LA3_0=='}') ) {s = 2;}

                        else if ( (LA3_0=='.'||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) && ((( inTag )||( !inTag )))) {s = 3;}

                        else if ( (LA3_0=='/') && ((( inTag )||( !inTag )))) {s = 4;}

                        else if ( (LA3_0=='#') && ((( inTag )||( !inTag )))) {s = 5;}

                        else if ( ((LA3_0>='\u0000' && LA3_0<='\"')||(LA3_0>='$' && LA3_0<='-')||(LA3_0>='0' && LA3_0<='@')||(LA3_0>='[' && LA3_0<='^')||LA3_0=='`'||LA3_0=='|'||(LA3_0>='~' && LA3_0<='\uFFFF')) && (( !inTag ))) {s = 6;}

                         
                        input.seek(index3_0);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA3_5>='\u0000' && LA3_5<='z')||(LA3_5>='|' && LA3_5<='\uFFFF')) && (( !inTag ))) {s = 6;}

                        else s = 10;

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}