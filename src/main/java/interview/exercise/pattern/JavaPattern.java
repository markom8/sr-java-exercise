package interview.exercise.pattern;

public final class JavaPattern {
    public static String wildcardToRegex(String wildcard) {
        String result = null;
        wildcard = wildcard.replace("'","");
        if (null != wildcard && !wildcard.trim().isEmpty()) {
            wildcard = wildcard.trim();
            StringBuilder s = new StringBuilder(wildcard.length());
            s.append('^');
            for (int i = 0, is = wildcard.length(); i < is; i++) {
                char c = wildcard.charAt(i);
                switch (c) {
                    case '*':
                        s.append(".*");
                        break;
                    case '?':
                        s.append(".");
                        break;
                    case '^':
                        s.append("\\");
                        break;
                    case '(':
                    case ')':
                    case '[':
                    case ']':
                    case '$':
                    case '.':
                    case '{':
                    case '}':
                    case '|':
                    case '\\':
                        s.append("\\");
                        s.append(c);
                        break;
                    default:
                        s.append(c);
                        break;
                }
            }
            s.append('$');
            result = (s.toString());
        }
        return result;
    }
}
