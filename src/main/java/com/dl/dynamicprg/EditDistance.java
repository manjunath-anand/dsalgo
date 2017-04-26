package com.dl.dynamicprg;

/**
 * Created by iruve on 21/4/17.
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 */
public class EditDistance {
    public static Integer[] indxf = new Integer[26];
    public static Integer[] indxt = new Integer[26];

    public static void getEditDistance(Character[] f, Character[] t) {
        for(int i = 0; i < t.length; i++) {
            int index = getIndex(t[i]);
            indxt[index] = i;
        }
        for(int i = 0; i < f.length; i++) {
            int index = getIndex(f[i]);
            indxf[index] = i;
        }
        int r = 0;
        for(int j = 0; j < t.length; j++) {
            if(f[r] == t[j]) {
                r++;
                continue;                           //Character[]{'S','U','M','A','N','D','A','Y'};
            }                                       //Character[]{'S','A','T','U','R','D','A','Y'};
            int index = getIndex(f[r]);
            int elemToCmp = (f.length-r) - (t.length-j);
            if(indxt[index] == null) {
                if(elemToCmp > 0 || (r < f.length-1 && f[r+1] == t[j])) {
                    System.out.println("Delete character "+f[r]+" at index1 "+r);
                    j--;
                } else {
                    System.out.println("Replace character "+f[r]+" at index "+r +" with charcter1 "+t[j]);
                }
                r++;
                continue;
            } else if(j < t.length-1 && f[r] == t[j+1] || elemToCmp < 0) {
                System.out.println("Insert character "+t[j]+" at index1 "+j);
                j++;
            } else if(r < f.length-1 && f[r+1] == t[j] || elemToCmp > 0){
                System.out.println("Delete character "+f[r]+" at index2 "+r);
                r++;
            } else {
                System.out.println("Replace character "+f[r]+" at index "+r +" with charcter2 "+t[j]);
                r++;
                j++;
            }
            if(f[r] == t[j]) {
                r++;
                continue;                           //Character[] f = {'S','M','N','A','T','D','A','Y'};
            }
            elemToCmp = (f.length-r) - (t.length-j);
            index = getIndex(t[j]);
            if(indxf[index] == null) {
                if(elemToCmp == 0) {
                    System.out.println("Replace character "+f[r]+" at index "+r +" with charcter3 "+t[j]);
                    r++;
                } else {
                    System.out.println("Insert character "+t[j]+" at index2 "+j);
                }
            }
        }
        System.out.println("******************************************************************************************************");
    }

    public static int getIndex(Character c) {
        return (int)((Character.toString(c)).toUpperCase().charAt(0)) - (int)'A';
    }

    public static void main(String[] a) {
        Character[] f = {'S','U','N','D','A','Y'};
        Character[] t = {'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','M','N','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','M','N','A','T','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','M','N','A','T','U','R','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','M','N','U','R','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','Y','A','T','U','R','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','U','A','T','N','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','U','M','A','N','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);

        f = new Character[]{'S','R','R','M','R','D','A','Y'};
        t = new Character[]{'S','A','T','U','R','D','A','Y'};

        getEditDistance(f,t);
    }
}
