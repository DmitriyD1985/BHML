# 1.Для подключени к базе данных изменить пароль и логин в файле persistence.properties на свои
# В pom.xml стоят в properties файле драйвер проставлен для mysql
#
# 2.Для того, чтобы создались таблицы в 31 строке файла persistence-config.xml сменить свойство none на create. После создания баз верните свойство none
# <prop key="hibernate.hbm2ddl.auto">none</prop>
#
# Появится страницу, сначала нужно инициализировать базу, для этого нажмите загрузить
# Потом можно понажимать кнопки для получения выгрузок
#
# NativeQuery реализован в AlbumsDAOimpl? в методе
# @Override
#     public List<Albums> getallAlbums() {
#         return entityManager.createNativeQuery("SELECT * FROM musicstore.albums").getResultList();
# }
# JPQL там же, в методе и в большинстве остальных методоыв
#     @Override
#     public Albums getByName(String albumName) {
#         Query q = entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
# q.setParameter("album", albumName);
# return (Albums) q.getSingleResult();
# }
# NamedQuery - текст запроса в Entity Songs
# @NamedQuery(name = "getAllSongs", query = "SELECT s FROM Songs s")
# Использован в SongsDAOImpl
#     @Override
#     public List<Songs> listOfSongs() {
#         return  entityManager.createNamedQuery("getAllSongs").getResultList();
# }