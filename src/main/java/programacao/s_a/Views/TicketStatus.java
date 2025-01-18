package programacao.s_a.Views;

public enum TicketStatus {
    OPEN {
        @Override
        public String toString() {
            return "Aberto";
        }
    },
    IN_PROGRESS {
        @Override
    public String toString() {
            return "Em andamento";
        }
    },
    AWAITING_CUSTOMER_ANSWER {
        @Override
        public String toString() {
            return "Em espera de Resposta";
        }
    },
    RESOLVED {
        @Override
        public String toString() {
            return "Resolvido";
        }
    },
    CANCELLED{
        @Override
        public String toString() {
            return "Cancelado";
        }
    }
}
