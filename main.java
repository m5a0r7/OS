import java.util.*;


public class main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        String process_name1 = sc.nextLine();

        String[] process_name = process_name1.split("\\s+");
        for (int i = 0; i < process_name.length; i++) {
            process_name[i] = process_name[i].replaceAll("[^\\w]", "");
        }



        String arrival_time1 = sc.nextLine();
        String[] arrival_time = arrival_time1.split("\\s+");
        for (int i = 0; i < arrival_time.length; i++) {
            arrival_time[i] = arrival_time[i].replaceAll("[^\\w]", "");
        }



        String[] arrival_time_for_fcfs = new String[arrival_time.length];

        for (int i =0;i<arrival_time.length;i++){
            arrival_time_for_fcfs[i] = arrival_time[i];
        }

        Arrays.sort(arrival_time_for_fcfs);
        String burst_time1 = sc.nextLine();
        String[] burst_time = burst_time1.split("\\s+");
        for (int i = 0; i < burst_time.length; i++) {
            burst_time[i] = burst_time[i].replaceAll("[^\\w]", "");
        }


        String[] arrival_time_for_fcfs_unique = unique(arrival_time_for_fcfs);
        Arrays.sort(arrival_time_for_fcfs_unique);


        List<String> output1 = new ArrayList<>();

        for (int i =0;i < arrival_time_for_fcfs_unique.length;i++){
            List<String> k = new ArrayList<>();
            for(int j = 0;j<arrival_time.length;j++){
                if(arrival_time[j] == arrival_time_for_fcfs_unique[i]){
                    k.add(process_name[j]);
                }
            }

            k.sort(String.CASE_INSENSITIVE_ORDER); //WORNING


            for (int r=0;r<k.size();r++){
                int s = index(process_name , k.get(r));
                int burst = Integer.parseInt(burst_time[s]);
                for(int n=0;n<burst;n++){
                    output1.add(k.get(r));
                }
            }
        }




        String[] output_for_fcfs = output1.toArray(new String[0]);
        for (int i =0;i<output_for_fcfs.length-1;i++){
            System.out.print(output_for_fcfs[i]);
        }
        System.out.println(output_for_fcfs[output_for_fcfs.length-1]);



        int time = Integer.parseInt(arrival_time_for_fcfs[0]);
        int check = 0;
        List<String> output2 = new ArrayList<>();

        List<Integer> arrivaltime_sjf = new ArrayList<>();
        for (int i=0;i<arrival_time.length;i++){
            arrivaltime_sjf.add(Integer.parseInt(arrival_time[i]));

        }


        int process_number = process_name.length;

        while (output2.size()<process_number){
            List<String> process_to_run = new ArrayList<>();
            List<String> process_to_run_with_sj = new ArrayList<>();
            for(int i=0;i<arrival_time.length;i++){
                if(arrivaltime_sjf.get(i)<=time){
                    if(!ismember(output2,process_name[i])){
                        process_to_run.add(process_name[i]);

                    }

                }


////////////from here
            }


            List<String> process_to_run_burst_time_remaining = new ArrayList<>();
            for (int p = 0;p< process_to_run.size();p++){
                int q = index(process_name,process_to_run.get(p));

                process_to_run_burst_time_remaining.add(burst_time[q]);
            }

            process_to_run_burst_time_remaining.sort(String.CASE_INSENSITIVE_ORDER);

            List<Integer> process_to_run_burst_time_remaining_Integer = new ArrayList<>();

            for(int hh=0;hh<process_to_run_burst_time_remaining.size();hh++){
                process_to_run_burst_time_remaining_Integer.add(Integer.parseInt(process_to_run_burst_time_remaining.get(hh)));
            }

            Collections.sort(process_to_run_burst_time_remaining_Integer);





            for(int g=0;g<process_to_run.size();g++){
                int h = index(process_name , process_to_run.get(g));

                if(Integer.parseInt(burst_time[h]) == process_to_run_burst_time_remaining_Integer.get(0)){
                    process_to_run_with_sj.add(process_name[h]);
                }


            }




            process_to_run_with_sj.sort(String.CASE_INSENSITIVE_ORDER); //WORNING


            if(process_to_run_with_sj.size()>0){
                output2.add(process_to_run_with_sj.get(0));
                int x = index(process_name , process_to_run_with_sj.get(0));
                time = time + Integer.parseInt(burst_time[x]);

            }



            if(process_to_run_with_sj.size()==0 && output2.size()<process_number){
                List<String> remaining_process_arrival_time = new ArrayList<>();
                for(int j = 0;j<process_number;j++){
                    if(!ismember(output2,process_name[j])){
                        remaining_process_arrival_time.add(arrival_time[j]);
                    }

                }
                List<String> process_to_run_burst_time_remaining_sj = new ArrayList<>();
                
                for (int w = 0;w<remaining_process_arrival_time.size();w++){
                    int a = index(process_name , remaining_process_arrival_time.get(w));
                    process_to_run_burst_time_remaining_sj.add(burst_time[a]);
                    
                }

                for(int k=0;k<arrival_time.length;k++){
                    if(arrival_time[k] == remaining_process_arrival_time.get(0)){

                        process_to_run.add(process_name[k]);

                    }
                }
                process_to_run_burst_time_remaining_sj.sort(String.CASE_INSENSITIVE_ORDER);
                for(int g=0;g<process_number;g++){
                    if(burst_time[g] == process_to_run_burst_time_remaining_sj.get(0)){
                        process_to_run_with_sj.add(process_name[g]);
                    }

                }

                process_to_run_with_sj.sort(String.CASE_INSENSITIVE_ORDER); //WORNING
                output2.add(process_to_run_with_sj.get(0));
                int h = index(process_name,process_to_run_burst_time_remaining_sj.get(0));
                time = Integer.parseInt(arrival_time[h]) + Integer.parseInt(burst_time[h]);

            }////

        }


        String[] o = output2.toArray(new String[0]);
        List<String> kkk = new ArrayList<>();

        for(int i = 0;i<process_number;i++){
            int a = index(process_name,o[i]);
            int burst = Integer.parseInt(burst_time[a]);
            for(int j = 0;j<burst;j++){
                kkk.add(o[i]);
            }

        }
        String[] output_for_sjf = kkk.toArray(new String[0]);

        for (int i =0;i<output_for_sjf.length-1;i++){
            System.out.print(output_for_sjf[i]);
        }
        System.out.println(output_for_sjf[output_for_sjf.length-1]);



        List<String> output3 = new ArrayList<>();

        int c = 0;
        for(int l= 0;l<process_number;l++){
            c = c + Integer.parseInt(burst_time[l]);

        }
        List<Integer> burst_time_sr = new ArrayList<>();
        for(int t=0;t<process_number;t++){
            burst_time_sr.add(Integer.parseInt(burst_time[t]));
        }

        int time3 = Integer.parseInt(arrival_time_for_fcfs[0]);

        
        while (output3.size() < c){
            List<Integer> process_to_run_burst_time_remaining = new ArrayList<>();
            List<String> process_to_run = new ArrayList<>();
            List<String> process_to_run_with_sr = new ArrayList<>();
            for(int i=0;i<arrival_time.length;i++){
                if(arrivaltime_sjf.get(i)<=time3){
                    if(burst_time_sr.get(i) > 0){
                        process_to_run.add(process_name[i]);

                    }

                }

            }
            if(process_to_run.size() > 0){




                for (int p=0;p<process_to_run.size();p++){
                    int q = index(process_name,process_to_run.get(p));
                    process_to_run_burst_time_remaining.add(burst_time_sr.get(q));
                }

                Collections.sort(process_to_run_burst_time_remaining);

                for(int g=0;g<process_to_run.size();g++){
                    int h = index(process_name , process_to_run.get(g));
                    if(burst_time_sr.get(h) == process_to_run_burst_time_remaining.get(0)){

                        process_to_run_with_sr.add(process_name[h]);
                    }

                }



                process_to_run_with_sr.sort(String.CASE_INSENSITIVE_ORDER); //WORNING

            }
            if(process_to_run_with_sr.size()>0){
                time3 = time3 + 1;
                int f = index(process_name , process_to_run_with_sr.get(0));

                output3.add(process_name[f]);
                burst_time_sr.set(f , burst_time_sr.get(f) -1);



            }

            if(process_to_run_with_sr.size() == 0){
                time3 = time3+1;
            }
        }

        String[] output_for_srf = output3.toArray(new String[0]);

        for (int i =0;i<output_for_srf.length-1;i++){
            System.out.print(output_for_srf[i]);
        }
        System.out.println(output_for_srf[output_for_srf.length-1]);



        List<Integer> ti = new ArrayList<>();
        ti.add(2);
        ti.add(4);
        ti.add(8);
        ti.add(20);

        for (int rr=0;rr<4;rr++){

            int rr_slot = ti.get(rr);



            List<String> queue = new ArrayList<>();
            List<String> output4 = new ArrayList<>();

            int time4 = Integer.parseInt(arrival_time_for_fcfs[0]);
            List<Integer> burst_time_rr = new ArrayList<>();
            for(int t=0;t<process_number;t++){
                burst_time_rr.add(Integer.parseInt(burst_time[t]));
            }

            for(int u =0;u < process_number;u++){
                if(Integer.parseInt(arrival_time[u]) == time4){
                    queue.add(process_name[u]);
                }
            }



            queue.sort(String.CASE_INSENSITIVE_ORDER); //WORNING
            //for(int f = 0;f<queue.size();f++){
            //System.out.println(queue.get(f));
            //}

            List<String> wating = new ArrayList<>();
            //System.out.println(queue.size());


            while (output4.size() < c) {



                if(queue.size()>0){

                    List<String> queue_process_with_min_brust = new ArrayList<>();

                    int h = index(process_name , queue.get(0));
                    int min_time = Integer.parseInt(arrival_time[h]);
                    for(int w =0;w<queue.size();w++){
                        int f = index(process_name , queue.get(w));
                        if(min_time == Integer.parseInt(arrival_time[f])){
                            queue_process_with_min_brust.add(queue.get(w));
                        }





                    }
                   // for(int u = 0; u<queue_process_with_min_brust.size();u++){
                       // System.out.println(queue_process_with_min_brust.get(u));
                    //}

                    //System.out.println("$$$$$$$$$$");



                    List<String> rm = new ArrayList<>();
                    List<String> Arrived_process = new ArrayList<>();

                    for(int n = 0;n<queue_process_with_min_brust.size();n++){
                        int h1 = index(process_name , queue_process_with_min_brust.get(n));

                        rm.add(queue_process_with_min_brust.get(n));
                        if(burst_time_rr.get(h1) >= rr_slot){
                            for (int d=0;d<rr_slot;d++){
                                output4.add(process_name[h1]);
                            }

                            time4 = time4 + rr_slot;

                            burst_time_rr.set(h1,burst_time_rr.get(h1) - rr_slot);
                        }

                        else{
                            for (int d=0;d<burst_time_rr.get(h1);d++){
                                output4.add(process_name[h1]);
                            }
                            time4 = time4 + burst_time_rr.get(h1);
                            burst_time_rr.set(h1,0);
                        }

                        if(burst_time_rr.get(h1) != 0){
                            wating.add(process_name[h1]);
                        }

                    }
                    for (int k=0 ; k<process_number;k++){
                        if(Integer.parseInt(arrival_time[k]) <= time4){
                            if(burst_time_rr.get(k) > 0){
                                Arrived_process.add(process_name[k]);
                            }
                        }
                    }

                    List<String> remove = new ArrayList<>();

                    for(int s =0;s<Arrived_process.size();s++){
                        if(ismember(queue , Arrived_process.get(s)) || ismember(wating , Arrived_process.get(s))){ ////Worning
                            remove.add(Arrived_process.get(s));
                        }
                    }

                    Arrived_process.removeAll(remove);

                    queue.removeAll(rm);


                    List<String> arrived_process_old = new ArrayList<>();
                    List<Integer> arrival_time_arrived_process_old = new ArrayList<>();
                    arrived_process_old.addAll(Arrived_process);

                    for(int jj = 0;jj<Arrived_process.size();jj++){
                        int hh = index(process_name , Arrived_process.get(jj));
                        arrival_time_arrived_process_old.add(Integer.parseInt(arrival_time[hh]));
                    }

                    Collections.sort(arrival_time_arrived_process_old);
                    List<Integer> arrival_time_arrived_process_old_unique = new ArrayList<>();

                    for(int tt=0;tt<arrival_time_arrived_process_old.size();tt++){
                        if(!ismember_int(arrival_time_arrived_process_old_unique,arrival_time_arrived_process_old.get(tt))){
                            arrival_time_arrived_process_old_unique.add(arrival_time_arrived_process_old.get(tt));
                        }
                    }

                    Collections.sort(arrival_time_arrived_process_old_unique);



                    for(int qq=0;qq<arrival_time_arrived_process_old_unique.size();qq++){
                        List<String> process_with_same_arrival_time = new ArrayList<>();
                        for(int kk=0;kk<arrived_process_old.size();kk++){
                            int ff = index(process_name , arrived_process_old.get(kk));
                            if(Integer.parseInt(arrival_time[ff]) == arrival_time_arrived_process_old_unique.get(qq)){
                                process_with_same_arrival_time.add(process_name[ff]);


                            }
                        }



                        Collections.sort(process_with_same_arrival_time);

                        //process_with_same_arrival_time.sort(String.CASE_INSENSITIVE_ORDER); //WORNING
                        Arrived_process.addAll(process_with_same_arrival_time);

                    }

                    for(int aa = 0;aa<arrived_process_old.size();aa++){
                        Arrived_process.remove(0);
                    }


                    queue.addAll(Arrived_process);

                }

                if(queue.size() ==0 && output4.size()<c && wating.size()==0){

                    List<String> remaining_process = new ArrayList<>();
                    List<String> process_to_run_from_remaining = new ArrayList<>();
                    List<String> remaining_burst_time = new ArrayList<>();
                    for(int e =0;e<process_number;e++){
                        if(!ismember(output4 , process_name[e])){
                            remaining_process.add(process_name[e]);
                        }
                    }

                    for(int t = 0;t<remaining_process.size();t++){
                        int r = index(process_name,remaining_process.get(t));
                        remaining_burst_time.add(arrival_time[r]);

                    }

                    remaining_burst_time.sort(String.CASE_INSENSITIVE_ORDER);

                    List<Integer> remaining_burst_time_Integer = new ArrayList<>();

                    for (int hh = 0;hh<remaining_burst_time.size();hh++){
                        remaining_burst_time_Integer.add(Integer.parseInt(remaining_burst_time.get(hh)));
                    }
                    Collections.sort(remaining_burst_time_Integer);


                    time4 = remaining_burst_time_Integer.get(0);

                    for(int aa = 0; aa<process_number;aa++){
                        if(Integer.parseInt(arrival_time[aa]) == remaining_burst_time_Integer.get(0)){
                            process_to_run_from_remaining.add(process_name[aa]);
                        }

                    }

                    process_to_run_from_remaining.sort(String.CASE_INSENSITIVE_ORDER);
                    queue.addAll(process_to_run_from_remaining);





                }

                if (queue.size() == 0 && output4.size() < c && wating.size() !=0){
                    queue.addAll(wating);
                    wating.clear();
                }



            }


            String[] output_for_rr_2 = output4.toArray(new String[0]);

            for (int i =0;i<output_for_rr_2.length-1;i++){
                System.out.print(output_for_rr_2[i]);
            }
            System.out.println(output_for_rr_2[output_for_rr_2.length-1]);


        }




/////////******








        sc.close();


    }


    public static List<Integer>  findIndex (int[] my_array, int t) {
        List<Integer> indexes = new ArrayList<Integer>();
        int len = my_array.length;
        int i = 0;
        while (i < len) {
            if (my_array[i] == t) {
                indexes.add(i);
            };
            i = i+1;
        }
        return indexes;
    }

    public static String[]  unique (String[] to_unique) {
        List<String> my_array = Arrays.asList(to_unique);
        List<String> a = new ArrayList<String>();
        int len = my_array.size();
        int i = 0;
        while (i < len) {
            if (!ismember(a , my_array.get(i))) {
                a.add(my_array.get(i));
            };
            i = i+1;
        }

        String[] b = a.toArray(new String[0]);
        return b;
    }

    public static boolean ismember(List<String> input, String a){

        int len = input.size();
        for(int i=0;i<len;i++){
            if(input.get(i) == a){
                return true;
            }
        }
        return false;


    }



    public static boolean ismember_int(List<Integer> input, Integer a){

        int len = input.size();
        for(int i=0;i<len;i++){
            if(input.get(i) == a){
                return true;
            }
        }
        return false;


    }

    public static int index(String[] in , String element){
        for(int i = 0;i<in.length;i++){
            if(in[i] == element){
                return i;
            }

        }
        return -1;
    }

    public static List<Integer> sum_for_list(List<Integer> inp , int num){

        List<Integer> a = new ArrayList<>();
        for(int i=0 ; i<inp.size();i++){
            a.add(inp.get(i) + num);
        }
        return a;
    }


    public static int index_of_last_occurrence(List<String> source , String sample){

        List<Integer> iii = new ArrayList<>();
        for(int i = 0;i<source.size();i++){
            if(source.get(i) == sample){
                iii.add(i);
            }
        }
        Collections.sort(iii);

        return iii.get(iii.size()-1);

    }

}



