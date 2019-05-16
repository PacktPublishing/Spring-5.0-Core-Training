public class DependencyInjection {

    static class Dependency {
        String doWork() {
            return "Work, done.";
        }
    }

    static class Injected {
        private Dependency dependency;

        Injected(Dependency dependency) {
            this.dependency = dependency;
        }

        void delegate() {
            System.out.println(dependency.doWork());
        }
    }

    public static void main(String[] args) {
        Dependency dependency = new Dependency();
        Injected injected = new Injected(dependency);

        injected.delegate();
    }
}
