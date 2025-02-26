package programacao.s_a.views;

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
    CLOSED {
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
