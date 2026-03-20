# CRC Cards Explanation

TaskManager collaborates with Task because it is responsible for storing, managing, and retrieving Task objects. It must interact with Task to access properties like taskId and status in order to perform its responsibilities.

Task does not collaborate with TaskManager because its responsibility is only to represent an individual task and manage its own data. It does not need to know how tasks are stored or managed at a higher level.