-- insert rows
INSERT INTO public.units (id, code, name)
VALUES (1, 6, 'МЕТР');
INSERT INTO public.units (id, code, name)
VALUES (2, 55, 'КВАДРАТНЫЙ МЕТР');
INSERT INTO public.units (id, code, name)
VALUES (3, 166, 'КИЛОГРАММ');
INSERT INTO public.units (id, code, name)
VALUES (4, 163, 'ГРАММ');
INSERT INTO public.units (id, code, name)
VALUES (5, 113, 'КУБИЧЕСКИЙ МЕТР');
INSERT INTO public.units (id, code, name)
VALUES (6, 112, 'ЛИТР');
INSERT INTO public.units (id, code, name)
VALUES (7, 797, 'СТО ШТУК');
INSERT INTO public.units (id, code, name)
VALUES (8, 715, 'ПАРА');
INSERT INTO public.units (id, code, name)
VALUES (9, 798, 'ТЫСЯЧА ШТУК');
INSERT INTO public.units (id, code, name)
VALUES (10, 796, 'ШТУКА');
INSERT INTO public.units (id, code, name)
VALUES (11, 306, 'ГРАММ ДЕЛЯЩИХСЯ ИЗОТОПОВ');
INSERT INTO public.units (id, code, name)
VALUES (12, 185, 'ГРУЗОПОДЪЕМНОСТЬ В ТОННАХ');
INSERT INTO public.units (id, code, name)
VALUES (13, 861, 'КИЛОГРАММ АЗОТА');
INSERT INTO public.units (id, code, name)
VALUES (14, 859, 'КИЛОГРАММ ГИДРОКСИДА КАЛИЯ');
INSERT INTO public.units (id, code, name)
VALUES (15, 863, 'КИЛОГРАММ ГИДРОКСИДА НАТРИЯ');
INSERT INTO public.units (id, code, name)
VALUES (16, 852, 'КИЛОГРАММ ОКСИДА КАЛИЯ');
INSERT INTO public.units (id, code, name)
VALUES (17, 841, 'КИЛОГРАММ ПЕРОКСИДА ВОДОРОДА');
INSERT INTO public.units (id, code, name)
VALUES (18, 865, 'КИЛОГРАММ ПЯТИОКИСИ ФОСФОРА');
INSERT INTO public.units (id, code, name)
VALUES (19, 845, 'КИЛОГРАММ 90 %-ГО СУХОГО ВЕЩЕСТВА');
INSERT INTO public.units (id, code, name)
VALUES (20, 867, 'КИЛОГРАММ УРАНА');
INSERT INTO public.units (id, code, name)
VALUES (21, 831, 'ЛИТР ЧИСТОГО (100%) СПИРТА');
INSERT INTO public.units (id, code, name)
VALUES (22, 162, 'МЕТРИЧЕСКИЙ КАРАТ(1КАРАТ=2*10(-4)КГ');
INSERT INTO public.units (id, code, name)
VALUES (23, 246, '1000 КИЛОВАТТ-ЧАС');
INSERT INTO public.units (id, code, name)
VALUES (24, 305, 'КЮРИ');
INSERT INTO public.units (id, code, name)
VALUES (25, 111, 'КУБИЧЕСКИЙ САНТИМЕТР');
INSERT INTO public.units (id, code, name)
VALUES (26, 130, '1000 ЛИТРОВ');
INSERT INTO public.units (id, code, name)
VALUES (27, 114, '1000 КУБИЧЕСКИХ МЕТРОВ');
INSERT INTO public.units (id, code, name)
VALUES (28, 168, 'ТОННА');
INSERT INTO public.units (id, code, name)
VALUES (29, 251, 'ЛОШАДИНАЯ СИЛА');
INSERT INTO public.units (id, code, name)
VALUES (30, 214, 'КИЛОВАТТ');

alter table units
    owner to postgres;
