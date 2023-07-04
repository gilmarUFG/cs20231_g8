

@Component
public class AuditEventListener implements ApplicationListener<AuditEvent> {

    @Autowired
    private IAuditService auditService;

    @Override
    public void onApplicationEvent(Object source, String entity, ObjectId entityId, String username) {
        
    }

}