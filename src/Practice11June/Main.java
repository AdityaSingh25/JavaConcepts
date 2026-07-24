//System.out.println();

package Practice11June;


import java.util.*;

class Demo {
    public Integer num;
    public String name;

    Demo(int a, String b) {
        this.num = a;
        this.name = b;
    }

    public void gh() {
        System.out.println("Demo");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

//        Integer a = new Integer(10);
//        Integer b = new Integer(10);
//        System.out.println(a == b); // false, because they are different objects
//        System.out.println(a.equals(b)); // true, because their values are equal]


        String s1 = "Adi";
        String s2 = "Adi";
        System.out.println(s1 == s2);

        String s3 = new String("Adi");
        String s4 = new String("Adi");
        System.out.println(s1 == s2);

        int[] arr = new int[5];
        arr[0] = 1;
        System.out.println("Array lenght : " + arr.length);

        Demo d = new Demo(2, "Adiyya");
        d.gh();


        // ––––––––––––––––––––––––––––––––

        // Array List

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.size());
        System.out.println(list.get(0));

        System.out.println("Before comparator : "+list);
        // to put it in descending order
        Collections.sort(list, new Comparator<Integer>(){

            @Override
            public int compare(Integer a, Integer b){
                if(a<b){
                    return 1;
                } else if(a>b){
                    return -1;
                }
                return 0;
            }
        });

        System.out.println("After comparator : "+list);

        //list.remove(pass_index) -> return the element removed

        list.add(1, 2); // you can pass index, element, but here the time complexity is more compared to list.add().
        list.clear(); // clear all list

        list.contains(2); // will return true or false.


        // LinkedList

        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.addFirst(2); // it will add in front

        ll.addLast(4); // will add in the last // its same as add() , why 2 functions doing same thing? as add comes from List interfase and addLast() comes from dque interface

        ll.removeFirst();
        ll.removeLast();

        ll.getFirst();
        ll.getLast();

        ll.contains(2);


        // Stack

        Stack<Integer> st = new Stack<>();
        st.push(1);
        System.out.println("--- "+st.peek()); // gives you the last element you pushed.


        st.pop(); // removed the last element you pushed
        st.isEmpty(); // true or false


        // Vector -> sames as array list but this is thread safe!!!


        System.out.println("–––––––––––––––");



        // HashMap does not store keys in sorted order **
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Adi");
        map.put(2, "Cosmo");
        map.put(3, "dwodw");
        map.put(4, "Addswedewi");

        System.out.println(map);

        // System.out.println();

        System.out.println(map.get(1));

        System.out.println(map.size());

        // Tree Map -> same but here keys are stored in sorted order


    }
}
