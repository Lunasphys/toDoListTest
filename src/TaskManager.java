import java.util.*;
import java.io.*;

public class TaskManager
{
    public static void main(String[] args) throws IOException {
        Task.menu();
    }



    public static class Task {
        public String nom;
        public boolean fini;

        static ArrayList<Task> taches = new ArrayList<>();

        public Task(String nom) {
            this.nom = nom;
            this.fini = false;
        }

        public String getNom() {
            return nom;
        }

        public boolean getComplession() {
            return fini;
        }
        public void setTask(boolean fini) {
            this.fini = fini;
        }
        public static void ajoutTache(ArrayList<Task> listeTache) throws IOException {
            BufferedReader lecteur = new BufferedReader(new InputStreamReader(System.in));
            String nomTache = lecteur.readLine();
            Task nouvelleTache = new Task(nomTache);
            listeTache.add(nouvelleTache);
            for(int i = 0; i < listeTache.size(); i++) {
                System.out.println((i+1) + " " + listeTache.get(i).getNom());
            }
        }

        public static void suppressionTacheChoix(ArrayList<Task> listeTache) throws IOException {
            System.out.println("Quelle tache souhaitez-vous supprimer ? (0 pour revenir au menu)");

            for(int i = 0; i < listeTache.size(); i++) {
                System.out.println((i+1) + " " + listeTache.get(i).getNom());
            }

            BufferedReader lecteur = new BufferedReader(new InputStreamReader(System.in));
            int choix = Integer.parseInt(lecteur.readLine());

            if ((choix >= 1) && (choix < listeTache.size())) {
                Task tacheASupprimer = listeTache.remove(choix - 1);
                System.out.println("Vous avez bien supprimer la tache " + tacheASupprimer.getNom() );
            } else if (choix == 0) {
                menu();
            } else {
                System.out.println("Choix non valide");
                suppressionTacheChoix(taches);
            }


        }

        public static void afficherTache(ArrayList<Task> taches) throws IOException {


            for(int i =0; i < taches.size(); i++) {
                if (!taches.get(i).getComplession()) {
                    System.out.println((i+1) + " [ ] " + taches.get(i).getNom());
                } else {
                    System.out.println((i+1) + " [x] " + taches.get(i).getNom());
                }

            }
            menu();
        }

        public static void tacheComplete(ArrayList<Task> taches) throws IOException {
            System.out.println("Quelle tache avez vous complété ?");

            for(int i = 0; i < taches.size(); i++) {
                System.out.println((i+1) + " " + taches.get(i).getNom());
            }

            BufferedReader lecteur = new BufferedReader(new InputStreamReader(System.in));
            int choix = Integer.parseInt(lecteur.readLine());

            if ((choix >= 1) && (choix < taches.size())) {
                Task tacheAValider = taches.get(choix - 1);
                tacheAValider.setTask(true);
                System.out.println("Vous avez bien validé la tache " + tacheAValider.getNom() );
            } else if (choix == 0) {
                menu();
            } else {
                System.out.println("Choix non valide");
                tacheComplete(taches);
            }

        }

        public static void menu() throws IOException {
            System.out.println("Veuillez choisir une option");
            BufferedReader lecteur = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1 : Ajouter une tâche");
            System.out.println("2 : Afficher les tâches");
            System.out.println("3 : Marquer une tâche comme terminée");
            System.out.println("4 : Supprimer une tâche");
            System.out.println("5 : Quitter");
            try {
                int choix = Integer.parseInt(lecteur.readLine());
                switch(choix) {
                    case 1 :
                        System.out.println("Ajouter une tâche");
                        ajoutTache(taches);
                        menu();

                        break;
                    case 2 :
                        System.out.println("Afficher les tâches");
                        afficherTache(taches);
                        menu();
                        break;
                    case 3 :
                        System.out.println("Marquer une tâche comme terminée");
                        tacheComplete(taches);
                        menu();
                        break;
                    case 4 :
                        System.out.println("Supprimer une tâche");
                        suppressionTacheChoix(taches);
                        menu();
                        break;
                    case 5 :
                        System.out.println("Quitter");
                        return;
                    default :
                        System.out.println("Mauvais choix");
                        menu();
                        break;

                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Mauvais choix");
                menu();
                throw new RuntimeException(e);

            }


        }
    }
}
