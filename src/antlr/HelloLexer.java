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
		T__0=1, WHILE=2, COMPARE=3, IF=4, ELSEIF=5, ELSE=6, RETURN=7, ARRAY=8, 
		OPENBLOCK=9, CLOSEBLOCK=10, OPENARRAY=11, CLOSEARRAY=12, OPENBRACKET=13, 
		CLOSEBRACKET=14, TOINT=15, TOREAL=16, DIVIDE=17, MULT=18, MINUS=19, PLUS=20, 
		STRING=21, ASSIGN=22, TO=23, TYPE=24, PRINT=25, READ=26, NEWLINE=27, ID=28, 
		WS=29, REAL=30, INT=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "WHILE", "COMPARE", "IF", "ELSEIF", "ELSE", "RETURN", "ARRAY", 
			"OPENBLOCK", "CLOSEBLOCK", "OPENARRAY", "CLOSEARRAY", "OPENBRACKET", 
			"CLOSEBRACKET", "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", "PLUS", 
			"STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", "ID", "WS", 
			"REAL", "INT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'while'", null, "'if'", "'else if'", "'else'", "'return'", 
			"'couple'", "'{'", "'}'", "'['", "']'", "'('", "')'", "'(int)'", "'(real)'", 
			"':/'", "':*'", "':('", "':)'", null, "'<3'", "'do'", null, "'flirt'", 
			"'listen'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "WHILE", "COMPARE", "IF", "ELSEIF", "ELSE", "RETURN", "ARRAY", 
			"OPENBLOCK", "CLOSEBLOCK", "OPENARRAY", "CLOSEARRAY", "OPENBRACKET", 
			"CLOSEBRACKET", "TOINT", "TOREAL", "DIVIDE", "MULT", "MINUS", "PLUS", 
			"STRING", "ASSIGN", "TO", "TYPE", "PRINT", "READ", "NEWLINE", "ID", "WS", 
			"REAL", "INT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00e5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4Q\n\4\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\26\3\26\7\26\u0098\n\26\f\26\16\26\u009b\13\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00b2\n\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\7\35\u00c7\n\35\f\35\16\35\u00ca\13\35\3\36\3\36\3\36\3\36\3\37\5\37"+
		"\u00d1\n\37\3\37\6\37\u00d4\n\37\r\37\16\37\u00d5\3\37\3\37\6\37\u00da"+
		"\n\37\r\37\16\37\u00db\3 \5 \u00df\n \3 \6 \u00e2\n \r \16 \u00e3\2\2"+
		"!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!\3\2\t\4\2>>@@\7\2\"\"\62;C\\aac|\4\2\f\f\17\17\4\2C\\c|\6\2\62;C"+
		"\\aac|\4\2\13\13\"\"\3\2\62;\2\u00f0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2"+
		"\2\2\5C\3\2\2\2\7P\3\2\2\2\tR\3\2\2\2\13U\3\2\2\2\r]\3\2\2\2\17b\3\2\2"+
		"\2\21i\3\2\2\2\23p\3\2\2\2\25r\3\2\2\2\27t\3\2\2\2\31v\3\2\2\2\33x\3\2"+
		"\2\2\35z\3\2\2\2\37|\3\2\2\2!\u0082\3\2\2\2#\u0089\3\2\2\2%\u008c\3\2"+
		"\2\2\'\u008f\3\2\2\2)\u0092\3\2\2\2+\u0095\3\2\2\2-\u009e\3\2\2\2/\u00a1"+
		"\3\2\2\2\61\u00b1\3\2\2\2\63\u00b3\3\2\2\2\65\u00b9\3\2\2\2\67\u00c0\3"+
		"\2\2\29\u00c4\3\2\2\2;\u00cb\3\2\2\2=\u00d0\3\2\2\2?\u00de\3\2\2\2AB\7"+
		".\2\2B\4\3\2\2\2CD\7y\2\2DE\7j\2\2EF\7k\2\2FG\7n\2\2GH\7g\2\2H\6\3\2\2"+
		"\2IQ\t\2\2\2JK\7>\2\2KQ\7?\2\2LM\7@\2\2MQ\7?\2\2NO\7?\2\2OQ\7?\2\2PI\3"+
		"\2\2\2PJ\3\2\2\2PL\3\2\2\2PN\3\2\2\2Q\b\3\2\2\2RS\7k\2\2ST\7h\2\2T\n\3"+
		"\2\2\2UV\7g\2\2VW\7n\2\2WX\7u\2\2XY\7g\2\2YZ\7\"\2\2Z[\7k\2\2[\\\7h\2"+
		"\2\\\f\3\2\2\2]^\7g\2\2^_\7n\2\2_`\7u\2\2`a\7g\2\2a\16\3\2\2\2bc\7t\2"+
		"\2cd\7g\2\2de\7v\2\2ef\7w\2\2fg\7t\2\2gh\7p\2\2h\20\3\2\2\2ij\7e\2\2j"+
		"k\7q\2\2kl\7w\2\2lm\7r\2\2mn\7n\2\2no\7g\2\2o\22\3\2\2\2pq\7}\2\2q\24"+
		"\3\2\2\2rs\7\177\2\2s\26\3\2\2\2tu\7]\2\2u\30\3\2\2\2vw\7_\2\2w\32\3\2"+
		"\2\2xy\7*\2\2y\34\3\2\2\2z{\7+\2\2{\36\3\2\2\2|}\7*\2\2}~\7k\2\2~\177"+
		"\7p\2\2\177\u0080\7v\2\2\u0080\u0081\7+\2\2\u0081 \3\2\2\2\u0082\u0083"+
		"\7*\2\2\u0083\u0084\7t\2\2\u0084\u0085\7g\2\2\u0085\u0086\7c\2\2\u0086"+
		"\u0087\7n\2\2\u0087\u0088\7+\2\2\u0088\"\3\2\2\2\u0089\u008a\7<\2\2\u008a"+
		"\u008b\7\61\2\2\u008b$\3\2\2\2\u008c\u008d\7<\2\2\u008d\u008e\7,\2\2\u008e"+
		"&\3\2\2\2\u008f\u0090\7<\2\2\u0090\u0091\7*\2\2\u0091(\3\2\2\2\u0092\u0093"+
		"\7<\2\2\u0093\u0094\7+\2\2\u0094*\3\2\2\2\u0095\u0099\7$\2\2\u0096\u0098"+
		"\t\3\2\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7$"+
		"\2\2\u009d,\3\2\2\2\u009e\u009f\7>\2\2\u009f\u00a0\7\65\2\2\u00a0.\3\2"+
		"\2\2\u00a1\u00a2\7f\2\2\u00a2\u00a3\7q\2\2\u00a3\60\3\2\2\2\u00a4\u00a5"+
		"\7K\2\2\u00a5\u00a6\7P\2\2\u00a6\u00b2\7V\2\2\u00a7\u00a8\7T\2\2\u00a8"+
		"\u00a9\7G\2\2\u00a9\u00aa\7C\2\2\u00aa\u00b2\7N\2\2\u00ab\u00ac\7U\2\2"+
		"\u00ac\u00ad\7V\2\2\u00ad\u00ae\7T\2\2\u00ae\u00af\7K\2\2\u00af\u00b0"+
		"\7P\2\2\u00b0\u00b2\7I\2\2\u00b1\u00a4\3\2\2\2\u00b1\u00a7\3\2\2\2\u00b1"+
		"\u00ab\3\2\2\2\u00b2\62\3\2\2\2\u00b3\u00b4\7h\2\2\u00b4\u00b5\7n\2\2"+
		"\u00b5\u00b6\7k\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7v\2\2\u00b8\64\3\2"+
		"\2\2\u00b9\u00ba\7n\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7u\2\2\u00bc\u00bd"+
		"\7v\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7p\2\2\u00bf\66\3\2\2\2\u00c0\u00c1"+
		"\t\4\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\b\34\2\2\u00c38\3\2\2\2\u00c4"+
		"\u00c8\t\5\2\2\u00c5\u00c7\t\6\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9:\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00cb\u00cc\t\7\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\b\36\2\2"+
		"\u00ce<\3\2\2\2\u00cf\u00d1\7/\2\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d4\t\b\2\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d9\7\60\2\2\u00d8\u00da\t\b\2\2\u00d9\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc>\3\2\2\2"+
		"\u00dd\u00df\7/\2\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1"+
		"\3\2\2\2\u00e0\u00e2\t\b\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4@\3\2\2\2\f\2P\u0099\u00b1\u00c8"+
		"\u00d0\u00d5\u00db\u00de\u00e3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}