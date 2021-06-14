// Generated from C:/Users/optiplex/IdeaProjects/MyLanguage1/src/antlr\Hello.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, DECREMENT=2, INCREMENT=3, WHILE=4, COMPARE=5, IF=6, ELSEIF=7, 
		ELSE=8, RETURN=9, ARRAY=10, OPENBLOCK=11, CLOSEBLOCK=12, OPENARRAY=13, 
		CLOSEARRAY=14, OPENBRACKET=15, CLOSEBRACKET=16, TOINT=17, TOREAL=18, DIVIDE=19, 
		MULT=20, MINUS=21, PLUS=22, STRING=23, ASSIGN=24, TO=25, TYPE=26, PRINT=27, 
		READ=28, NEWLINE=29, ID=30, WS=31, REAL=32, INT=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "DECREMENT", "INCREMENT", "WHILE", "COMPARE", "IF", "ELSEIF", 
			"ELSE", "RETURN", "ARRAY", "OPENBLOCK", "CLOSEBLOCK", "OPENARRAY", "CLOSEARRAY", 
			"OPENBRACKET", "CLOSEBRACKET", "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", 
			"PLUS", "STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", 
			"ID", "WS", "REAL", "INT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':(('", "':))'", "'while'", null, "'if'", "'else if'", 
			"'else'", "'return'", "'couple'", "'{'", "'}'", "'['", "']'", "'('", 
			"')'", "'(single)'", "'(taken)'", "':/'", "':*'", "':('", "':)'", null, 
			"'<3'", "'do'", null, "'flirt'", "'listen'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "DECREMENT", "INCREMENT", "WHILE", "COMPARE", "IF", "ELSEIF", 
			"ELSE", "RETURN", "ARRAY", "OPENBLOCK", "CLOSEBLOCK", "OPENARRAY", "CLOSEARRAY", 
			"OPENBRACKET", "CLOSEBRACKET", "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", 
			"PLUS", "STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", 
			"ID", "WS", "REAL", "INT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public HelloLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00fd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6"+
		"e\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\7\30"+
		"\u00b0\n\30\f\30\16\30\u00b3\13\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\5\33\u00ca\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\7\37\u00df\n\37\f\37\16\37\u00e2"+
		"\13\37\3 \3 \3 \3 \3!\5!\u00e9\n!\3!\6!\u00ec\n!\r!\16!\u00ed\3!\3!\6"+
		"!\u00f2\n!\r!\16!\u00f3\3\"\5\"\u00f7\n\"\3\"\6\"\u00fa\n\"\r\"\16\"\u00fb"+
		"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#\3\2\b\7\2\"\"\62;C\\aac|\4\2\f\f\17\17\4\2C\\c|\6\2\62;"+
		"C\\aac|\4\2\13\13\"\"\3\2\62;\2\u0109\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5G\3\2\2\2\7K\3\2\2\2\tO\3\2\2\2\13d\3\2"+
		"\2\2\rf\3\2\2\2\17i\3\2\2\2\21q\3\2\2\2\23v\3\2\2\2\25}\3\2\2\2\27\u0084"+
		"\3\2\2\2\31\u0086\3\2\2\2\33\u0088\3\2\2\2\35\u008a\3\2\2\2\37\u008c\3"+
		"\2\2\2!\u008e\3\2\2\2#\u0090\3\2\2\2%\u0099\3\2\2\2\'\u00a1\3\2\2\2)\u00a4"+
		"\3\2\2\2+\u00a7\3\2\2\2-\u00aa\3\2\2\2/\u00ad\3\2\2\2\61\u00b6\3\2\2\2"+
		"\63\u00b9\3\2\2\2\65\u00c9\3\2\2\2\67\u00cb\3\2\2\29\u00d1\3\2\2\2;\u00d8"+
		"\3\2\2\2=\u00dc\3\2\2\2?\u00e3\3\2\2\2A\u00e8\3\2\2\2C\u00f6\3\2\2\2E"+
		"F\7.\2\2F\4\3\2\2\2GH\7<\2\2HI\7*\2\2IJ\7*\2\2J\6\3\2\2\2KL\7<\2\2LM\7"+
		"+\2\2MN\7+\2\2N\b\3\2\2\2OP\7y\2\2PQ\7j\2\2QR\7k\2\2RS\7n\2\2ST\7g\2\2"+
		"T\n\3\2\2\2UV\7<\2\2Ve\7>\2\2WX\7<\2\2Xe\7@\2\2YZ\7<\2\2Z[\7>\2\2[e\7"+
		"\65\2\2\\]\7<\2\2]^\7@\2\2^_\7>\2\2_e\7\65\2\2`a\7>\2\2ab\7\65\2\2bc\7"+
		">\2\2ce\7\65\2\2dU\3\2\2\2dW\3\2\2\2dY\3\2\2\2d\\\3\2\2\2d`\3\2\2\2e\f"+
		"\3\2\2\2fg\7k\2\2gh\7h\2\2h\16\3\2\2\2ij\7g\2\2jk\7n\2\2kl\7u\2\2lm\7"+
		"g\2\2mn\7\"\2\2no\7k\2\2op\7h\2\2p\20\3\2\2\2qr\7g\2\2rs\7n\2\2st\7u\2"+
		"\2tu\7g\2\2u\22\3\2\2\2vw\7t\2\2wx\7g\2\2xy\7v\2\2yz\7w\2\2z{\7t\2\2{"+
		"|\7p\2\2|\24\3\2\2\2}~\7e\2\2~\177\7q\2\2\177\u0080\7w\2\2\u0080\u0081"+
		"\7r\2\2\u0081\u0082\7n\2\2\u0082\u0083\7g\2\2\u0083\26\3\2\2\2\u0084\u0085"+
		"\7}\2\2\u0085\30\3\2\2\2\u0086\u0087\7\177\2\2\u0087\32\3\2\2\2\u0088"+
		"\u0089\7]\2\2\u0089\34\3\2\2\2\u008a\u008b\7_\2\2\u008b\36\3\2\2\2\u008c"+
		"\u008d\7*\2\2\u008d \3\2\2\2\u008e\u008f\7+\2\2\u008f\"\3\2\2\2\u0090"+
		"\u0091\7*\2\2\u0091\u0092\7u\2\2\u0092\u0093\7k\2\2\u0093\u0094\7p\2\2"+
		"\u0094\u0095\7i\2\2\u0095\u0096\7n\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7+\2\2\u0098$\3\2\2\2\u0099\u009a\7*\2\2\u009a\u009b\7v\2\2\u009b\u009c"+
		"\7c\2\2\u009c\u009d\7m\2\2\u009d\u009e\7g\2\2\u009e\u009f\7p\2\2\u009f"+
		"\u00a0\7+\2\2\u00a0&\3\2\2\2\u00a1\u00a2\7<\2\2\u00a2\u00a3\7\61\2\2\u00a3"+
		"(\3\2\2\2\u00a4\u00a5\7<\2\2\u00a5\u00a6\7,\2\2\u00a6*\3\2\2\2\u00a7\u00a8"+
		"\7<\2\2\u00a8\u00a9\7*\2\2\u00a9,\3\2\2\2\u00aa\u00ab\7<\2\2\u00ab\u00ac"+
		"\7+\2\2\u00ac.\3\2\2\2\u00ad\u00b1\7$\2\2\u00ae\u00b0\t\2\2\2\u00af\u00ae"+
		"\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7$\2\2\u00b5\60\3\2\2\2"+
		"\u00b6\u00b7\7>\2\2\u00b7\u00b8\7\65\2\2\u00b8\62\3\2\2\2\u00b9\u00ba"+
		"\7f\2\2\u00ba\u00bb\7q\2\2\u00bb\64\3\2\2\2\u00bc\u00bd\7K\2\2\u00bd\u00be"+
		"\7P\2\2\u00be\u00ca\7V\2\2\u00bf\u00c0\7T\2\2\u00c0\u00c1\7G\2\2\u00c1"+
		"\u00c2\7C\2\2\u00c2\u00ca\7N\2\2\u00c3\u00c4\7U\2\2\u00c4\u00c5\7V\2\2"+
		"\u00c5\u00c6\7T\2\2\u00c6\u00c7\7K\2\2\u00c7\u00c8\7P\2\2\u00c8\u00ca"+
		"\7I\2\2\u00c9\u00bc\3\2\2\2\u00c9\u00bf\3\2\2\2\u00c9\u00c3\3\2\2\2\u00ca"+
		"\66\3\2\2\2\u00cb\u00cc\7h\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7k\2\2\u00ce"+
		"\u00cf\7t\2\2\u00cf\u00d0\7v\2\2\u00d08\3\2\2\2\u00d1\u00d2\7n\2\2\u00d2"+
		"\u00d3\7k\2\2\u00d3\u00d4\7u\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7g\2\2"+
		"\u00d6\u00d7\7p\2\2\u00d7:\3\2\2\2\u00d8\u00d9\t\3\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00db\b\36\2\2\u00db<\3\2\2\2\u00dc\u00e0\t\4\2\2\u00dd\u00df"+
		"\t\5\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1>\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\t\6\2\2"+
		"\u00e4\u00e5\3\2\2\2\u00e5\u00e6\b \2\2\u00e6@\3\2\2\2\u00e7\u00e9\7/"+
		"\2\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea"+
		"\u00ec\t\7\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\7\60\2\2\u00f0"+
		"\u00f2\t\7\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f3\u00f4\3\2\2\2\u00f4B\3\2\2\2\u00f5\u00f7\7/\2\2\u00f6\u00f5"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00fa\t\7\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fcD\3\2\2\2\f\2d\u00b1\u00c9\u00e0\u00e8\u00ed\u00f3\u00f6\u00fb"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}