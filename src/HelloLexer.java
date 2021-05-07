// Generated from C:/Users/optiplex/IdeaProjects/MyLanguage1/src\Hello.g4 by ANTLR 4.9.1
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
		T__0=1, T__1=2, TOINT=3, TOREAL=4, DIVIDE=5, MULT=6, MINUS=7, PLUS=8, 
		STRING=9, ASSIGN=10, TO=11, TYPE=12, PRINT=13, READ=14, NEWLINE=15, ID=16, 
		WS=17, REAL=18, INT=19, ARRAY=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", "PLUS", 
			"STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", "ID", "WS", 
			"REAL", "INT", "ARRAY"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'(int)'", "'(real)'", "'/'", "'*'", "':-'", "':+'", 
			null, "'='", "'do'", null, "'wypisz'", "'odczytaj'", null, null, null, 
			null, null, "'tablica'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", "PLUS", 
			"STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", "ID", "WS", 
			"REAL", "INT", "ARRAY"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u00a1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\n\3\n\7\nI\n\n\f\n\16\nL\13\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rb\n\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\5\20u\n\20\3\20\3\20\3\21\3\21\7\21{\n\21\f\21\16\21~\13\21\3\22\3"+
		"\22\3\22\3\22\3\23\5\23\u0085\n\23\3\23\6\23\u0088\n\23\r\23\16\23\u0089"+
		"\3\23\3\23\6\23\u008e\n\23\r\23\16\23\u008f\3\24\5\24\u0093\n\24\3\24"+
		"\6\24\u0096\n\24\r\24\16\24\u0097\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\7\7\2\"\"\62;C\\aac|\4\2C\\c"+
		"|\6\2\62;C\\aac|\4\2\13\13\"\"\3\2\62;\2\u00aa\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\65\3\2\2\2\13<\3\2"+
		"\2\2\r>\3\2\2\2\17@\3\2\2\2\21C\3\2\2\2\23F\3\2\2\2\25O\3\2\2\2\27Q\3"+
		"\2\2\2\31a\3\2\2\2\33c\3\2\2\2\35j\3\2\2\2\37t\3\2\2\2!x\3\2\2\2#\177"+
		"\3\2\2\2%\u0084\3\2\2\2\'\u0092\3\2\2\2)\u0099\3\2\2\2+,\7*\2\2,\4\3\2"+
		"\2\2-.\7+\2\2.\6\3\2\2\2/\60\7*\2\2\60\61\7k\2\2\61\62\7p\2\2\62\63\7"+
		"v\2\2\63\64\7+\2\2\64\b\3\2\2\2\65\66\7*\2\2\66\67\7t\2\2\678\7g\2\28"+
		"9\7c\2\29:\7n\2\2:;\7+\2\2;\n\3\2\2\2<=\7\61\2\2=\f\3\2\2\2>?\7,\2\2?"+
		"\16\3\2\2\2@A\7<\2\2AB\7/\2\2B\20\3\2\2\2CD\7<\2\2DE\7-\2\2E\22\3\2\2"+
		"\2FJ\7$\2\2GI\t\2\2\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2"+
		"\2LJ\3\2\2\2MN\7$\2\2N\24\3\2\2\2OP\7?\2\2P\26\3\2\2\2QR\7f\2\2RS\7q\2"+
		"\2S\30\3\2\2\2TU\7K\2\2UV\7P\2\2Vb\7V\2\2WX\7T\2\2XY\7G\2\2YZ\7C\2\2Z"+
		"b\7N\2\2[\\\7U\2\2\\]\7V\2\2]^\7T\2\2^_\7K\2\2_`\7P\2\2`b\7I\2\2aT\3\2"+
		"\2\2aW\3\2\2\2a[\3\2\2\2b\32\3\2\2\2cd\7y\2\2de\7{\2\2ef\7r\2\2fg\7k\2"+
		"\2gh\7u\2\2hi\7|\2\2i\34\3\2\2\2jk\7q\2\2kl\7f\2\2lm\7e\2\2mn\7|\2\2n"+
		"o\7{\2\2op\7v\2\2pq\7c\2\2qr\7l\2\2r\36\3\2\2\2su\7\17\2\2ts\3\2\2\2t"+
		"u\3\2\2\2uv\3\2\2\2vw\7\f\2\2w \3\2\2\2x|\t\3\2\2y{\t\4\2\2zy\3\2\2\2"+
		"{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\"\3\2\2\2~|\3\2\2\2\177\u0080\t\5\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\u0082\b\22\2\2\u0082$\3\2\2\2\u0083\u0085\7"+
		"/\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0088\t\6\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\7\60\2\2\u008c"+
		"\u008e\t\6\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090&\3\2\2\2\u0091\u0093\7/\2\2\u0092\u0091"+
		"\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\t\6\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098(\3\2\2\2\u0099\u009a\7v\2\2\u009a\u009b\7c\2\2\u009b\u009c"+
		"\7d\2\2\u009c\u009d\7n\2\2\u009d\u009e\7k\2\2\u009e\u009f\7e\2\2\u009f"+
		"\u00a0\7c\2\2\u00a0*\3\2\2\2\f\2Jat|\u0084\u0089\u008f\u0092\u0097\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}