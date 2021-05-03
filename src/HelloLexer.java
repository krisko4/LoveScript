// Generated from C:/Users/adm/OneDrive - Politechnika Warszawska/Dokumenty/MyCustomLanguage/src\Hello.g4 by ANTLR 4.9.1
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
		T__0=1, T__1=2, DIVIDE=3, MULT=4, MINUS=5, PLUS=6, STRING=7, ASSIGN=8, 
		TYPE=9, AND=10, SUBTRACT=11, FROM=12, ADD=13, TO=14, PRINT=15, READ=16, 
		NEWLINE=17, ID=18, WS=19, REAL=20, INT=21, ARRAY=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "DIVIDE", "MULT", "MINUS", "PLUS", "STRING", "ASSIGN", 
			"TYPE", "AND", "SUBTRACT", "FROM", "ADD", "TO", "PRINT", "READ", "NEWLINE", 
			"ID", "WS", "REAL", "INT", "ARRAY"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'/'", "'*'", "':-'", "':+'", null, "'='", null, 
			"'oraz'", "'odejmij'", "'od'", "'dodaj'", "'do'", "'wypisz'", "'odczytaj'", 
			null, null, null, null, null, "'tablica'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "DIVIDE", "MULT", "MINUS", "PLUS", "STRING", "ASSIGN", 
			"TYPE", "AND", "SUBTRACT", "FROM", "ADD", "TO", "PRINT", "READ", "NEWLINE", 
			"ID", "WS", "REAL", "INT", "ARRAY"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00ae\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\7\b@\n\b\f\b\16\bC"+
		"\13\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\nV\n\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\5\22\u0082\n\22\3\22\3\22\3\23\3\23\7\23\u0088\n\23\f\23\16\23\u008b"+
		"\13\23\3\24\3\24\3\24\3\24\3\25\5\25\u0092\n\25\3\25\6\25\u0095\n\25\r"+
		"\25\16\25\u0096\3\25\3\25\6\25\u009b\n\25\r\25\16\25\u009c\3\26\5\26\u00a0"+
		"\n\26\3\26\6\26\u00a3\n\26\r\26\16\26\u00a4\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\7\7\2\"\""+
		"\62;C\\aac|\4\2C\\c|\6\2\62;C\\aac|\4\2\13\13\"\"\3\2\62;\2\u00b7\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5"+
		"\61\3\2\2\2\7\63\3\2\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r:\3\2\2\2\17=\3\2"+
		"\2\2\21F\3\2\2\2\23U\3\2\2\2\25W\3\2\2\2\27\\\3\2\2\2\31d\3\2\2\2\33g"+
		"\3\2\2\2\35m\3\2\2\2\37p\3\2\2\2!w\3\2\2\2#\u0081\3\2\2\2%\u0085\3\2\2"+
		"\2\'\u008c\3\2\2\2)\u0091\3\2\2\2+\u009f\3\2\2\2-\u00a6\3\2\2\2/\60\7"+
		"*\2\2\60\4\3\2\2\2\61\62\7+\2\2\62\6\3\2\2\2\63\64\7\61\2\2\64\b\3\2\2"+
		"\2\65\66\7,\2\2\66\n\3\2\2\2\678\7<\2\289\7/\2\29\f\3\2\2\2:;\7<\2\2;"+
		"<\7-\2\2<\16\3\2\2\2=A\7$\2\2>@\t\2\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2"+
		"AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7$\2\2E\20\3\2\2\2FG\7?\2\2G\22\3\2\2"+
		"\2HI\7K\2\2IJ\7P\2\2JV\7V\2\2KL\7T\2\2LM\7G\2\2MN\7C\2\2NV\7N\2\2OP\7"+
		"U\2\2PQ\7V\2\2QR\7T\2\2RS\7K\2\2ST\7P\2\2TV\7I\2\2UH\3\2\2\2UK\3\2\2\2"+
		"UO\3\2\2\2V\24\3\2\2\2WX\7q\2\2XY\7t\2\2YZ\7c\2\2Z[\7|\2\2[\26\3\2\2\2"+
		"\\]\7q\2\2]^\7f\2\2^_\7g\2\2_`\7l\2\2`a\7o\2\2ab\7k\2\2bc\7l\2\2c\30\3"+
		"\2\2\2de\7q\2\2ef\7f\2\2f\32\3\2\2\2gh\7f\2\2hi\7q\2\2ij\7f\2\2jk\7c\2"+
		"\2kl\7l\2\2l\34\3\2\2\2mn\7f\2\2no\7q\2\2o\36\3\2\2\2pq\7y\2\2qr\7{\2"+
		"\2rs\7r\2\2st\7k\2\2tu\7u\2\2uv\7|\2\2v \3\2\2\2wx\7q\2\2xy\7f\2\2yz\7"+
		"e\2\2z{\7|\2\2{|\7{\2\2|}\7v\2\2}~\7c\2\2~\177\7l\2\2\177\"\3\2\2\2\u0080"+
		"\u0082\7\17\2\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3"+
		"\2\2\2\u0083\u0084\7\f\2\2\u0084$\3\2\2\2\u0085\u0089\t\3\2\2\u0086\u0088"+
		"\t\4\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a&\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\t\5\2\2"+
		"\u008d\u008e\3\2\2\2\u008e\u008f\b\24\2\2\u008f(\3\2\2\2\u0090\u0092\7"+
		"/\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0095\t\6\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\7\60\2\2\u0099"+
		"\u009b\t\6\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d*\3\2\2\2\u009e\u00a0\7/\2\2\u009f\u009e"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\t\6\2\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5,\3\2\2\2\u00a6\u00a7\7v\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9"+
		"\7d\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7e\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad.\3\2\2\2\f\2AU\u0081\u0089\u0091\u0096\u009c\u009f"+
		"\u00a4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}