# _*_ coding:utf-8 _*_
from urllib.parse import quote
import requests
from json import loads

# 1f9f7f82bfc4d9bd3bb9fc6d5d6efbdf
def cut(s):
    s = s.replace('<b>', '')
    s = s.replace('</b>', '')
    s = s.replace('&amp;', '')
    s = s.replace('&gt;', '')
    s = s.replace('&lt;', '')
    s = s.replace('&quot;', '')
    s = s.replace('[', '')
    s = s.replace(']', '')
    s = s.replace('(', '')
    s = s.replace(')', '')
    return s

if __name__ == '__main__':
    q = quote('드루킹')
    u = 'https://dapi.kakao.com/v2/search/blog?sort=recency&size=50&query=' + q
    h = {'Authorization': 'KakaoAK 1f9f7f82bfc4d9bd3bb9fc6d5d6efbdf'}
    
    res = requests.get(url=u, headers=h)
    #print(res.text)
    #f = open('C:/dwm/bigData/kakaoBlogJson.txt', 'a', encoding='utf-8')
    f = open('/home/dwm/myBigData/kakaoBlogJson.txt', 'a', encoding='utf-8')
    
    # 전체
    json = loads(res.text)
    documents = json['documents']
    
    d, c, t, bn = None, None, None, None
    for blog in documents:
        d = cut(blog['datetime'])
        c = cut(blog['contents'])
        t = cut(blog['title'])
        bn = cut(blog['blogname'])
        txt = "%s\t%s\t%s\t%s\n" % (d, c, t, bn)
        print(txt)
        f.write(txt)
        
    f.close()