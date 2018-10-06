// Generated from E:\!PROJECTS\IntelliJ IDEA\MyLang/src/main/antlr/MyLang.g4 by ANTLR 4.7.1
package generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MyLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, ID=7, NUMBER=8, STRING=9, 
		LPAREN=10, RPAREN=11, LBRACE=12, RBRACE=13, SEMI=14, COMMA=15, ASSIGN=16, 
		GT=17, LT=18, COLON=19, EQUAL=20, LE=21, GE=22, NOTEQUAL=23, AND=24, OR=25, 
		ADD=26, SUB=27, MUL=28, DIV=29, ADD_ASSIGN=30, SUB_ASSIGN=31, MUL_ASSIGN=32, 
		DIV_ASSIGN=33, WS=34, COMMENT=35, LINE_COMMENT=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "ID", "NUMBER", "STRING", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "ASSIGN", "GT", 
		"LT", "COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "ADD", "SUB", 
		"MUL", "DIV", "ADD_ASSIGN", "SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", 
		"WS", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'int'", "'void'", "'if'", "'else'", "'for'", "'print'", null, null, 
		null, "'('", "')'", "'{'", "'}'", "';'", "','", "'='", "'>'", "'<'", "':'", 
		"'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", 
		"'+='", "'-='", "'*='", "'/='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "ID", "NUMBER", "STRING", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "ASSIGN", "GT", "LT", "COLON", 
		"EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "ADD", "SUB", "MUL", "DIV", 
		"ADD_ASSIGN", "SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "WS", "COMMENT", 
		"LINE_COMMENT"
	};
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


	public MyLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MyLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u00d5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\7\bi\n\b\f\b\16\bl\13\b\3\t\6\to\n\t\r\t\16\tp\3\n\3\n\7\nu\n\n"+
		"\f\n\16\nx\13\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3"+
		"!\3!\3\"\3\"\3\"\3#\6#\u00b7\n#\r#\16#\u00b8\3#\3#\3$\3$\3$\3$\7$\u00c1"+
		"\n$\f$\16$\u00c4\13$\3$\3$\3$\3$\3$\3%\3%\3%\3%\7%\u00cf\n%\f%\16%\u00d2"+
		"\13%\3%\3%\3\u00c2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2\b\5\2C\\aac|\6\2\62;C\\aac|\3"+
		"\2\62;\3\2$$\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u00da\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K"+
		"\3\2\2\2\5O\3\2\2\2\7T\3\2\2\2\tW\3\2\2\2\13\\\3\2\2\2\r`\3\2\2\2\17f"+
		"\3\2\2\2\21n\3\2\2\2\23r\3\2\2\2\25{\3\2\2\2\27}\3\2\2\2\31\177\3\2\2"+
		"\2\33\u0081\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2\2\2!\u0087\3\2\2\2#"+
		"\u0089\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u008f\3\2\2\2+\u0092\3"+
		"\2\2\2-\u0095\3\2\2\2/\u0098\3\2\2\2\61\u009b\3\2\2\2\63\u009e\3\2\2\2"+
		"\65\u00a1\3\2\2\2\67\u00a3\3\2\2\29\u00a5\3\2\2\2;\u00a7\3\2\2\2=\u00a9"+
		"\3\2\2\2?\u00ac\3\2\2\2A\u00af\3\2\2\2C\u00b2\3\2\2\2E\u00b6\3\2\2\2G"+
		"\u00bc\3\2\2\2I\u00ca\3\2\2\2KL\7k\2\2LM\7p\2\2MN\7v\2\2N\4\3\2\2\2OP"+
		"\7x\2\2PQ\7q\2\2QR\7k\2\2RS\7f\2\2S\6\3\2\2\2TU\7k\2\2UV\7h\2\2V\b\3\2"+
		"\2\2WX\7g\2\2XY\7n\2\2YZ\7u\2\2Z[\7g\2\2[\n\3\2\2\2\\]\7h\2\2]^\7q\2\2"+
		"^_\7t\2\2_\f\3\2\2\2`a\7r\2\2ab\7t\2\2bc\7k\2\2cd\7p\2\2de\7v\2\2e\16"+
		"\3\2\2\2fj\t\2\2\2gi\t\3\2\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k"+
		"\20\3\2\2\2lj\3\2\2\2mo\t\4\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2"+
		"\2q\22\3\2\2\2rv\7$\2\2su\n\5\2\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2"+
		"\2\2wy\3\2\2\2xv\3\2\2\2yz\7$\2\2z\24\3\2\2\2{|\7*\2\2|\26\3\2\2\2}~\7"+
		"+\2\2~\30\3\2\2\2\177\u0080\7}\2\2\u0080\32\3\2\2\2\u0081\u0082\7\177"+
		"\2\2\u0082\34\3\2\2\2\u0083\u0084\7=\2\2\u0084\36\3\2\2\2\u0085\u0086"+
		"\7.\2\2\u0086 \3\2\2\2\u0087\u0088\7?\2\2\u0088\"\3\2\2\2\u0089\u008a"+
		"\7@\2\2\u008a$\3\2\2\2\u008b\u008c\7>\2\2\u008c&\3\2\2\2\u008d\u008e\7"+
		"<\2\2\u008e(\3\2\2\2\u008f\u0090\7?\2\2\u0090\u0091\7?\2\2\u0091*\3\2"+
		"\2\2\u0092\u0093\7>\2\2\u0093\u0094\7?\2\2\u0094,\3\2\2\2\u0095\u0096"+
		"\7@\2\2\u0096\u0097\7?\2\2\u0097.\3\2\2\2\u0098\u0099\7#\2\2\u0099\u009a"+
		"\7?\2\2\u009a\60\3\2\2\2\u009b\u009c\7(\2\2\u009c\u009d\7(\2\2\u009d\62"+
		"\3\2\2\2\u009e\u009f\7~\2\2\u009f\u00a0\7~\2\2\u00a0\64\3\2\2\2\u00a1"+
		"\u00a2\7-\2\2\u00a2\66\3\2\2\2\u00a3\u00a4\7/\2\2\u00a48\3\2\2\2\u00a5"+
		"\u00a6\7,\2\2\u00a6:\3\2\2\2\u00a7\u00a8\7\61\2\2\u00a8<\3\2\2\2\u00a9"+
		"\u00aa\7-\2\2\u00aa\u00ab\7?\2\2\u00ab>\3\2\2\2\u00ac\u00ad\7/\2\2\u00ad"+
		"\u00ae\7?\2\2\u00ae@\3\2\2\2\u00af\u00b0\7,\2\2\u00b0\u00b1\7?\2\2\u00b1"+
		"B\3\2\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\7?\2\2\u00b4D\3\2\2\2\u00b5"+
		"\u00b7\t\6\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\b#\2\2\u00bb"+
		"F\3\2\2\2\u00bc\u00bd\7\61\2\2\u00bd\u00be\7,\2\2\u00be\u00c2\3\2\2\2"+
		"\u00bf\u00c1\13\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c3"+
		"\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5"+
		"\u00c6\7,\2\2\u00c6\u00c7\7\61\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b$"+
		"\2\2\u00c9H\3\2\2\2\u00ca\u00cb\7\61\2\2\u00cb\u00cc\7\61\2\2\u00cc\u00d0"+
		"\3\2\2\2\u00cd\u00cf\n\7\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d0\3\2"+
		"\2\2\u00d3\u00d4\b%\2\2\u00d4J\3\2\2\2\t\2jpv\u00b8\u00c2\u00d0\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}