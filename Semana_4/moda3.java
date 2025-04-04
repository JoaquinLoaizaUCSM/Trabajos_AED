import java.util.*;

public class moda3
{

    static class Limits 
    {
        int[] a;
        int prim;
        int ult;
        
        public Limits(int[] a, int prim, int ult) 
        {
            this.a = a;
            this.prim = prim;
            this.ult = ult;
        }
    }

    static class SetVectors 
    {
        private List<Limits> set;

        public SetVectors() 
        {
            set = new ArrayList<>();
        }

        public void insertar(Limits p) 
        {
            set.add(p);
        }

        public Limits mayor() 
        {
            Limits mayor = null;
            for (Limits l : set) 
            {
                if (mayor == null || (l.ult - l.prim) > (mayor.ult - mayor.prim)) 
                {
                    mayor = l;
                }
            }
            return mayor;
        }

        public boolean esVacio() 
        {
            return set.isEmpty();
        }

        public int longMayor() 
        {
            if (set.isEmpty()) return 0;
            return (mayor().ult - mayor().prim);
        }

        public void destruir() 
        {
            set.clear();
        }
    }

    public static void pivote2(int[] a, int mediana, int prim, int ult, int[] izq, int[] der) 
    {
        int i = prim, j = ult;
        while (i <= j) 
        {
            while (a[i] < mediana) i++;
            while (a[j] > mediana) j--;
            if (i <= j) 
            {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        izq[0] = i;
        der[0] = j;
    }

    public static int moda3(int[] a, int prim, int ult)
     {
        Limits p, p1, p2, p3;
        SetVectors homogeneo = new SetVectors();
        SetVectors heterogeneo = new SetVectors();
        int mediana;
        int[] izq = new int[1], der = new int[1];

        p = new Limits(a, prim, ult);
        heterogeneo.insertar(p);

        while (heterogeneo.longMayor() > homogeneo.longMayor()) 
        {
            p = heterogeneo.mayor();
            mediana = a[(p.prim + p.ult) / 2];
            pivote2(a, mediana, p.prim, p.ult, izq, der);
            
            p1 = new Limits(a, p.prim, izq[0] - 1);
            p2 = new Limits(a, izq[0], der[0]);
            p3 = new Limits(a, der[0] + 1, p.ult);

            if (p1.prim <= p1.ult) heterogeneo.insertar(p1);
            if (p3.prim <= p3.ult) heterogeneo.insertar(p3);
            if (p2.prim <= p2.ult) homogeneo.insertar(p2);
        }

        if (homogeneo.esVacio()) return a[prim];

        p = homogeneo.mayor();
        heterogeneo.destruir();
        homogeneo.destruir();
        return a[p.prim];
    }
}