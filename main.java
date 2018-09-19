import java.util.*;

public class main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        String a = sc.nextLine();

        int length_of_page_number = 0;

        String[] aa = a.split("\\s+");

        if(aa.length == 1){
            length_of_page_number = 1;
        }

        int is_numeric_page_number = 0;
        int page_number = 0;

        if(aa[0].matches("-?\\d+(\\.\\d+)?")){
            page_number = Integer.parseInt(aa[0]);
            is_numeric_page_number = 1;
        }




        String reference_string1 = sc.nextLine();
        String[] reference_string = reference_string1.split("\\s+");
        for (int i = 0; i < reference_string.length; i++) {
            reference_string[i] = reference_string[i].replaceAll("[^\\w]", "");
        }
        String algorithm1 = sc.nextLine();
        String[] algorithm = algorithm1.split("\\s+");
        for (int i = 0; i < algorithm.length; i++) {
            algorithm[i] = algorithm[i].replaceAll("[^\\w]", "");
        }


        int is_alogorithm_with_length_2 = 0;
        String algorithm_type = "a";
        if(algorithm.length == 2){
            algorithm_type = algorithm[1].toString();
            is_alogorithm_with_length_2 = 1;
        }

        if(page_number == 0){
            reference_string = new String[0];
        }






        int is_numeric_frame_number = 0;
        int frame_number = 0;
        if(algorithm[0].matches("-?\\d+(\\.\\d+)?")){
            frame_number = Integer.parseInt(algorithm[0]);
            is_numeric_frame_number = 1;
        }



        int page_fault_number = 0;

        int n = 0;

        int first_in = 0;



        List<String> output_list = new ArrayList<>();


        List<String> current_frame = new ArrayList<>();
        List<Integer> queue = new ArrayList<>();

        int check = 0;

        if((frame_number == 3 | frame_number == 4 | frame_number==5) & algorithm.length == 2  & page_number < 10000 & 0<= page_number & is_numeric_frame_number == 1 & is_numeric_page_number == 1 & is_alogorithm_with_length_2 == 1 & length_of_page_number == 1){

            check = 1;

        }



        int numeric_check = 0;

        int page_number_is_equal_to_refrence_length = 0;

        for(int k = 0;k<reference_string.length;k++){

            boolean numeric;

            numeric = reference_string[k].matches("-?\\d+(\\.\\d+)?");

            if(numeric){
                numeric_check = numeric_check + 1;
            }
        }

        if(page_number == reference_string.length){
            page_number_is_equal_to_refrence_length = 1;
        }

        int frame_number_check = 0;

        boolean numeric_frame_number_check;
        numeric_frame_number_check = algorithm[0].matches("-?\\d+(\\.\\d+)?");

        if(numeric_frame_number_check){
            frame_number_check = 1;
        }


        int ss = 0;





        List<String> fifo = new ArrayList<>();


        if((page_number_is_equal_to_refrence_length == 1) & (frame_number_check == 1) & (numeric_check == page_number) & check == 1){





            if(algorithm_type.equals("LRU") | algorithm_type.equals("FIFO")){


                if(page_number > frame_number){    ///if page_nimber <= frame_number

                    if(algorithm_type.equals("LRU")){


                        while ((current_frame.size() < frame_number)& n < reference_string.length){
                            if(!current_frame.contains(reference_string[n].toString())){

                                current_frame.add(reference_string[n].toString());

                                if(queue.size() == 0){
                                    queue.add(0);
                                }

                                else {
                                    queue.add(Collections.max(queue) + 1);
                                }

                                String[] s1 = current_frame.toArray(new String[0]);

                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n] + " " + s + " " + "page fault";
                                output_list.add(o);
                                System.out.println(o);
                                page_fault_number = page_fault_number + 1;
                            }

                            else{
                                String[] s1 = current_frame.toArray(new String[0]);

                                ss = current_frame.indexOf(reference_string[n].toString());


                                queue = sum_for_list(queue,1);
                                queue.set(ss , 0);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n] + " " + s;
                                output_list.add(o);
                                System.out.println(o);
                            }

                            n = n+1;

                        }




                        while (n < page_number){

                            n = n+1;
                            String next_string = reference_string[n-1].toString();
                            if(current_frame.contains(next_string)){
                                String[] s1 = current_frame.toArray(new String[0]);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n-1] + " " + s;
                                ss = current_frame.indexOf(next_string);
                                queue.set(ss , Collections.max(queue)+1);
                                //queue = mod_for_list(queue,frame_number);
                                //queue = mod_for_list(queue,Collections.max(queue));
                                output_list.add(o);
                                System.out.println(o);

                            }

                            else{
                                //int find_max = new Integer(queue.indexOf(Collections.max(queue)));
                                //current_frame.set(find_max,next_string);
                                //queue = sum_for_list(queue,1);
                                //queue = mod_for_list(queue,frame_number);
                                //queue = mod_for_list(queue,Collections.max(queue));
                                ss = queue.indexOf(Collections.min(queue));
                                current_frame.set(ss , next_string);
                                queue.set(ss , Collections.max(queue)+1);

                                String[] s1 = current_frame.toArray(new String[0]);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n-1] + " " + s + " " + "page fault";
                                output_list.add(o);
                                page_fault_number = page_fault_number + 1;
                                System.out.println(o);
                            }


                        }


                        String result = "total number of page faults is " + page_fault_number;

                        System.out.println(result);
                    }


                    if(algorithm_type.equals("FIFO")){



                        while ((current_frame.size() < frame_number) & n < reference_string.length){
                            if(!current_frame.contains(reference_string[n].toString())){

                                current_frame.add(reference_string[n].toString());

                                String[] s1 = current_frame.toArray(new String[0]);

                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n] + " " + s + " " + "page fault";
                                output_list.add(o);
                                System.out.println(o);
                                page_fault_number = page_fault_number + 1;

                            }

                            else{
                                String[] s1 = current_frame.toArray(new String[0]);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n] + " " + s;
                                output_list.add(o);
                                System.out.println(o);
                            }
                            n = n+1;
                        }

                        /*
                        for(int i=0;i < frame_number;i++){
                            current_frame.add(reference_string[i].toString());

                            String[] s1 = current_frame.toArray(new String[0]);

                            String s = Arrays.toString(s1);
                            s = s.replace("," , "");
                            String o = reference_string[i] + " " + s + " " + "page fault";
                            output_list.add(o);
                            System.out.println(o);

                        }

                        */

                        fifo.addAll(current_frame);

                        while (n < page_number){

                            n = n+1;
                            String next_string = reference_string[n-1].toString();
                            if(current_frame.contains(next_string)){
                                String[] s1 = current_frame.toArray(new String[0]);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n-1] + " " + s;
                                output_list.add(o);
                                System.out.println(o);

                            }

                            if(!current_frame.contains(next_string)){
                                first_in = current_frame.indexOf(fifo.get(0));
                                current_frame.set(first_in,next_string);
                                fifo.remove(0);
                                fifo.add(next_string);
                                String[] s1 = current_frame.toArray(new String[0]);
                                String s = Arrays.toString(s1);
                                s = s.replace("," , "");
                                String o = reference_string[n-1] + " " + s + " " + "page fault";
                                output_list.add(o);
                                page_fault_number = page_fault_number + 1;
                                System.out.println(o);
                            }


                        }

                        String result = "total number of page faults is " + page_fault_number;

                        System.out.println(result);

                    }



                }

                if(page_number <= frame_number){



                    for(int i=0;i < page_number;i++){


                        if(!current_frame.contains(reference_string[i].toString())){

                            current_frame.add(reference_string[i].toString());

                            String[] s1 = current_frame.toArray(new String[0]);

                            String s = Arrays.toString(s1);
                            s = s.replace("," , "");
                            String o = reference_string[i] + " " + s + " " + "page fault";
                            output_list.add(o);
                            System.out.println(o);
                        }

                        else{
                            String[] s1 = current_frame.toArray(new String[0]);
                            String s = Arrays.toString(s1);
                            s = s.replace("," , "");
                            String o = reference_string[n] + " " + s;
                            output_list.add(o);
                            System.out.println(o);
                        }
                    }


                    String result = "total number of page faults is " + page_number;

                    System.out.println(result);






                }




            }



            if(!(algorithm_type.equals("LRU") | algorithm_type.equals("FIFO"))){

                System.out.println("error");
            }

        }



        if(!((page_number_is_equal_to_refrence_length == 1) & (frame_number_check == 1) & (numeric_check == page_number) & check == 1& is_numeric_frame_number == 1 & is_numeric_page_number == 1& is_alogorithm_with_length_2 == 1 & length_of_page_number == 1)){

            System.out.println("error");
        }



    }



    public static List<Integer> sum_for_list(List<Integer> inp , int num){

        List<Integer> a = new ArrayList<>();
        for(int i=0 ; i<inp.size();i++){
            a.add(inp.get(i) + num);
        }
        return a;
    }


    public static List<Integer> mod_for_list(List<Integer> inp , int num){

        List<Integer> a = new ArrayList<>();
        for(int i=0 ; i<inp.size();i++){
            a.add(inp.get(i) % num);
        }
        return a;
    }


}
