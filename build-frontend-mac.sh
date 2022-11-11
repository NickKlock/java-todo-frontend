# to run the script you need to have node installed (brew install node)

mkdir ./backend/src/main/resources/static

cd ./frontend || exit

export NODE_OPTIONS=--openssl-legacy-provider
npm install
npm run build
cd ..

cp -R ./frontend/build/* ./backend/src/main/resources/static