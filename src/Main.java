import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Grafo {
    public List<Vertice> vertices = new ArrayList<Vertice>();
    public List<Aresta> arestas = new ArrayList<Aresta>();

    public void alterarTipo(int a, int b, String tipo){
        for (int i = 0; i < arestas.size(); i++){
            if ((arestas.get(i).aresta[0] == a) && (arestas.get(i).aresta[1] == b)){
                arestas.get(i).tipo = tipo;
            }
        }
    }
    public Boolean verificarTipo(String teste, String tipo){
        String[] teste1 = teste.split("-");
        int a = Integer.parseInt(teste1[0]);
        int b = Integer.parseInt(teste1[1]);

        for (int i = 0; i < arestas.size(); i++){
            if ((arestas.get(i).aresta[0] == (a-1)) && (arestas.get(i).aresta[1] == (b-1))){
                if (arestas.get(i).tipo == tipo){
                    return true;
                }
            }
        }
        return false;
    }
}

final class Aresta {
    public int[] aresta = new int[2];
    public String tipo = null;

    public Aresta(int[] pAresta){
        this.aresta = pAresta;
    }

}

final class Vertice {
    public int num;
    //public Boolean visitado = false;
    public List<Vertice> adjacentes = new ArrayList<Vertice>();
    public int expl = 0;
    public int comp = 0;

    public Vertice(int pNum){
        this.num = pNum;
    }
}

final class Main {

    public static void Tarjan(){
        for (int i = 0; i < grafo.vertices.size(); i++){
            if (grafo.vertices.get(i).expl == 0){
                con++;
                DFST(grafo.vertices.get(i));
            }
        }
    }

    public static void DFST(Vertice v){
        ce++;
        v.expl = ce;
        for (int i = 0; i < v.adjacentes.size(); i++){
            if (v.adjacentes.get(i).expl == 0){
                grafo.alterarTipo(v.num, v.adjacentes.get(i).num, "arvore");
                DFST(v.adjacentes.get(i));
            } else {
                if (v.adjacentes.get(i).expl > v.expl){
                    grafo.alterarTipo(v.num, v.adjacentes.get(i).num, "avanco");
                } else {
                    if (v.adjacentes.get(i).comp > 0){
                        grafo.alterarTipo(v.num, v.adjacentes.get(i).num, "cruzamento");
                    } else {
                        grafo.alterarTipo(v.num, v.adjacentes.get(i).num, "retorno");
                    }
                }
            }
        }
        cc++;
        v.comp = cc;
    }

    public static Boolean verificar(String[] testes){
        Boolean resultado = null;
        //System.out.println(testes[0]);
        if (testes[0].equals("conectado")){
            if (con > 1){
                resultado = false;
            } else {
                resultado = true;
            }
        } else if (testes[0].equals("arvore")){
            resultado = grafo.verificarTipo(testes[1], "arvore");
        } else if (testes[0].equals("retorno")){
            resultado = grafo.verificarTipo(testes[1], "retorno");
        } else if (testes[0].equals("avanco")){
            resultado = grafo.verificarTipo(testes[1], "avanco");
        } else if (testes[0].equals("cruzamento")){
            resultado = grafo.verificarTipo(testes[1], "cruzamento");
        }
        return resultado;
    }

    static int ce = 0;
    static int cc = 0;
    static Grafo grafo = new Grafo();
    static int con = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Grafo grafo = new Grafo();
        List<Boolean> saida = new ArrayList<Boolean>();

        Scanner in = new Scanner(System.in);
        //int numVertices = in.nextInt();
        String[] valores = in.nextLine().split("\\s+");
        //in.nextLine();
        int v1 = Integer.parseInt(valores[0]);
        int v2 = Integer.parseInt(valores[1]);

        for (int i = 0; i < v1; i++){
            grafo.vertices.add(new Vertice(i));
        }

        for (int i = 0; i < v1; i++){
            String[] linha = in.nextLine().split("\\s+");
            //in.nextLine();
            for (int j = 0; j < linha.length; j++) {
                int n = Integer.parseInt(linha[j]);
                if (n != 0) {
                    grafo.vertices.get(i).adjacentes.add(grafo.vertices.get(j));
                }
            }
        }

        for (int i = 0; i < grafo.vertices.size(); i++){
            for (int j = 0; j < grafo.vertices.get(i).adjacentes.size(); j++){
                int[] aresta = new int[2];
                aresta[0] = grafo.vertices.get(i).num;
                aresta[1] = grafo.vertices.get(i).adjacentes.get(j).num;
                grafo.arestas.add(new Aresta(aresta));
            }
        }

        Tarjan();

        for (int i = 0; i < v2; i++){
            String[] linha = in.nextLine().split("\\s+");
            //in.nextLine();
            saida.add(verificar(linha));

        }

        for (int i = 0; i < saida.size(); i++){
            if (saida.get(i)){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

        /*for (int i = 0; i < grafo.arestas.size(); i++){
            System.out.println(grafo.arestas.get(i).aresta[0]+"-"+grafo.arestas.get(i).aresta[1]+" "+grafo.arestas.get(i).tipo);
        }*/

        /*for (int i = 0; i < grafo.arestas.size(); i++){
            Aresta a = grafo.arestas.get(i);
            System.out.println(a.aresta[0] +" "+ a.aresta[1]);
            System.out.println();
        }*/

        /*for (int i = 0; i < grafo.vertices.size(); i++){
            Vertice x = grafo.vertices.get(i);
            for (int j = 0; j < x.adjacentes.size(); j++){
                System.out.print(x.adjacentes.get(j).num + " ");
            }
            System.out.println();
        }*/

        /*for (int i = 0; i < grafo.vertices.size(); i++){
            Vertice x = grafo.vertices.get(i);
            System.out.println(x.expl);
            System.out.println(x.comp);
            System.out.println();
        }*/

    }

}
