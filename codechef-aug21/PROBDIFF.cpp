#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    int T, a;
    int n = 4;
    cin >> T;
    for (int i = 0; i < T; ++i) {
        unordered_map<int, int> umap;
        for (int j = 0; j < n; ++j) {
            cin >> a;
            umap[a]++;
        }
        vector<int> vec(umap.size());
        for (auto item : umap) {
            vec.push_back(item.second);
        }
        sort(vec.begin(), vec.end(), greater<int>());
        int cnt = 0;
        int m = 0;
        for (auto item : vec) {
            cnt += min(m, item);
            m = abs(m - item);
        }
        cout << cnt << "\n";
    }
    return 0;
}
