package leetcode_solutions.str;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses929 {
    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> set = new HashSet<>();
            for (String email : emails
            ) {
                set.add(deformEmail(email));
            }
            return set.size();
        }

        private String deformEmail(String email) {
            String[] strs = email.split("@");
            String[] ss = strs[0].split("\\+");
            StringBuilder sb = new StringBuilder();
            sb.append(ss[0].replace(".", "")).append("@").append(strs[1]);
            return sb.toString();
        }
    }

    class SolutionOnLeetcode1 {
        public int numUniqueEmails(String[] emails) {
            if (emails == null || emails.length == 0) {
                return 0;
            }

            Set<String> emailAddresses = new HashSet<>();
            for (String email : emails) {
                String processedEmail = process(email);
                emailAddresses.add(processedEmail);
            }
            return emailAddresses.size();
        }

        private String process(String email) {
            int atSplitterIndex = email.indexOf('@');
            String localName = email.substring(0, atSplitterIndex);
            String domainName = email.substring(atSplitterIndex);
            StringBuilder sb = new StringBuilder();
            char[] chars = localName.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '+') {
                    break;
                }
                if (chars[i] != '.') {
                    sb.append(chars[i]);
                }
            }

            sb.append(domainName);
            return sb.toString();
        }

    }
}
