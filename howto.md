1. Клонировали репозиторий KotlinAsFirst2020
2. Указали ему в качестве апстрима upstream-my свой форк KotlinAsFirst2021 (git remote add upstream-my https://github.com/RolandTheSoldier/KotlinAsFirst2021)
3. Создали ветку backport (git checkout -b backport) и перенесли в неё свои коммиты с решениями из 2021 года (git fetch upstream; git merge upstream/master)
4. Указали второй апстрим upstream-theirs форка KotlinAsFirst2021 (git remote add upstream-theirs https://github.com/LeeryLis/KotlinAsFirst2021.git)
5. Смёрджили решения в ветку master (git fetch upstream-theirs; git merge upstream-theirs/master; git add .; коммит; пуш)
6. Создали в корне проекта файл remotes, куда поместили вывод git remote -v
7. Создали в корне проекта файл howto.md, в котором описали ход выполнения практики
