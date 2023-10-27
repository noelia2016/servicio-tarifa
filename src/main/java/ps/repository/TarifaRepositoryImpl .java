public final class TarifaRepositoryImpl implements TarifaRepository {

    private final CopyOnWriteArrayList<Tarifa> eList = new ArrayList<Tarifa>();

    public TarifaRepositoryImpl() {
        JsonbConfig config = new JsonbConfig().withFormatting(Boolean.TRUE);

        Jsonb jsonb = JsonbBuilder.create(config);

        eList.addAll(jsonb.fromJson(TarifaRepositoryImpl.class.getResourceAsStream("/tarifa.json"),
                new ArrayList<Tarifa>() {}.getClass().getGenericSuperclass()));
    }

    // retorna la lista de tarifas que hay
    @Override
    public List<Tarifa> getAll() {
        return eList;
    }

    // busca en la lista y devuelve null si no se encuentra ningún ID coincidente.
    @Override
    public Tarifa getById(String id) {
        Tarifa tarifa;
        tarifa = eList.stream().filter(e -> e.getId().equals(id)).findFirst().get();
        return tarifa;
    }

    // guarda una nueva Tarifa para los monopatines
    @Override
    public Tarifa save(Tarifa tarifa) {
        Tarifa nextTarifa = tTarifa.of(null, tarifa.getTarifaNormal(), tarifa.getTarifaExtra(),
            tarifa.getFechaDesde(),tarifa.getFechaHasta()); 
        eList.add(nextTarifa);
        return nextTarifa;
    }

    // actualiza un objeto existente suprimiendo el objeto antiguo y creando un nuevo objeto con los nuevos datos. 
    @Override
    public Tarifa update(Tarifa updatedTarifa, String id) {
        deleteById(id);
        Tarifa t = tarifa.of(id, tarifa.tarifa.getTarifaNormal(), tarifa.getTarifaExtra(),
        tarifa.getFechaDesde(),tarifa.getFechaHasta());
        eList.add(t);
        return t;
    }

    //El método sólo busca el ID de objetos y, a continuación, obtiene el índice del elemento de lista. El índice de lista se utiliza para suprimir el elemento de lista.
    @Override
    public void deleteById(String id) {
        int matchIndex;
        matchIndex = eList.stream().filter(e -> e.getId().equals(id)).findFirst().map(e -> eList.indexOf(e)).get();
        eList.remove(matchIndex);
    }

}