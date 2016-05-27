select timediff(
    (select update_time from information_schema.tables where table_schema='users' and table_name='courses'),
    (select create_time from information_schema.tables where table_schema='users' and table_name='users')
) as data_load_time_diff;

